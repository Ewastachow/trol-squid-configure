package trol.domain.log;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import trol.domain.util.FileHelper;
import trol.domain.terminal.TerminalExecute;

import java.io.IOException;
import java.util.*;

@Component
public class LogsReader {

    private static final Logger log = LoggerFactory.getLogger(LogsReader.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private final BlockTimeManager timeManager;
    private final TerminalExecute term;
    private final String accessLogPath;
    private int lastLine;

    private volatile LogState state = LogState.FREE;
    public LogState getState() {
        return state;
    }

    LogsReader() {
        this("/var/log/squid/trolUserTimes.log", new TerminalExecute());
    }

    LogsReader(String accessLogPath, TerminalExecute term) {
        this.term = term;
        this.accessLogPath = accessLogPath;
        timeManager = new BlockTimeManager();
        lastLine = 0;
    }

    /**
     * Should be called in equal intervals by other thread or deamon,
     * reads squid logs and update users Used Time using UserDAO
     * @throws IOException problems with log file
     */
    @Scheduled(fixedDelay=10000)
    @Async
    public void checkUsersLogs() throws IOException, InterruptedException {

        if (state.equals(LogState.BUSY)){
            System.out.println("nie przerywac, pracuje");
            return;
        }
        System.out.println("zaczynam prace "+ this);
        state = LogState.BUSY;

        if(timeManager.nextDay()) {
            log.info("Try to clear used times", dateFormat.format(new Date()));
            updateNextDay();
        }
        else {
            log.info("Try to check users logs", dateFormat.format(new Date()));
            updateUsersReadyToBlock();
        }

        System.out.println("koncze prace "+ this);
        state = LogState.FREE;
    }

    private void updateNextDay() throws IOException, InterruptedException {
        timeManager.clearUsedTime();
        lastLine = 0;
        term.executeCommand("squid -k rotate");
    }

    private void updateUsersReadyToBlock() throws IOException {
        Map<String,Integer> usersSeconds = new HashMap<>();

        List<String> newLines = FileHelper.readLastLinesSince(accessLogPath, lastLine);
        lastLine += newLines.size();

        if(newLines.isEmpty()) {
            log.info("Brak nowych logów", dateFormat.format(new Date()));
            return;
        }

        for (String l : newLines)
            parseLine(usersSeconds,l);

        usersSeconds.forEach((u,t) -> timeManager.updateUserTime(u,t));

    }

    private void parseLine(Map<String,Integer> usersSeconds, String line) {
        String user = line.split("[\t ]+")[0];
        if(usersSeconds.containsKey(user))
            usersSeconds.put(user,usersSeconds.get(user)+10);
        else
            usersSeconds.put(user,10);
    }
}
