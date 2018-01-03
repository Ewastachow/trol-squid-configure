package trol.domain.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import trol.dao.users.UserDAO;
import trol.dao.users.UserDAOImpl;
import trol.domain.filemanager.FileController;
import trol.domain.util.FileHelper;
import trol.domain.terminal.TerminalExecute;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.*;

public class LogsReader {

    private volatile LogState state = LogState.FREE;
    public LogState getState() {
        return state;
    }

    @Autowired
    private FileController fileController;
    @Autowired
    private UserDAO userDAO;

    private BlockTimeManager timeManager;
    private TerminalExecute term;
    private String accessLogPath;
    private int lastLine;

    LogsReader(TerminalExecute term) {
        this("/var/log/squid/trolUserTimes.log", term);
    }

    LogsReader(String accessLogPath, TerminalExecute term) {
        this.term = term;
        this.accessLogPath = accessLogPath;
        timeManager = new BlockTimeManager();
        lastLine = 0;
    }

    /**
     * Should be called in equal intervals by other thread or deamon,
     * reads squid logs and update users usedTime using UserDAO
     * @throws UnknownHostException When users list is invalid.
     * @throws IOException problems with logs or block times file
     */
    @Async
    public void checkUsersLogs() throws IOException, InterruptedException {
        if(timeManager.nextDay()) {
            updateNextDay();
        }
        else {
            updateUsersReadyToBlock();
        }

        fileController.saveConfiguration();
    }

    private void updateNextDay() throws IOException, InterruptedException {
        timeManager.clearWastedTime();
        lastLine = 0;
        term.executeCommand("squid -k rotate");
    }

    private void updateUsersReadyToBlock() throws IOException {
        Map<String,Integer> usersSeconds = new HashMap<>();

        List<String> newLines = FileHelper.readLastLinesSince(accessLogPath, lastLine);
        lastLine += newLines.size();

        for (String l : newLines)
            parseLine(usersSeconds,l);

        usersSeconds.forEach((u,t) -> timeManager.updateUserTime(u,t));
    }

    private void parseLine(Map<String,Integer> usersSeconds, String line) {
        String user = line.replaceAll("\\[|\\]","").split("[\t ]+")[0];
        if(usersSeconds.containsKey(user))
            usersSeconds.put(user,usersSeconds.get(user)+10);
        else
            usersSeconds.put(user,10);
    }
}
