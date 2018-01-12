package trol.domain.log;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import trol.dao.users.UserDAO;
import trol.domain.filemanager.FileController;
import trol.domain.filemanager.FilePaths;
import trol.domain.trol_api.model.User;
import trol.domain.util.FileHelper;
import trol.domain.terminal.TerminalExecute;

import java.io.IOException;
import java.util.*;

@Component
public class UsedTimeManager {

    @Autowired
    private FileController fileController;
    @Autowired
    private UserDAO userDAO;

    private static final Logger log = LoggerFactory.getLogger(UsedTimeManager.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private final TerminalExecute term;
    private final String accessLogPath;
    private int lastLine;
    private LocalTime lastUpdateTimestamp;

    private volatile UsedTimeManagerState state = UsedTimeManagerState.FREE;
    public UsedTimeManagerState getState() {
        return state;
    }

    UsedTimeManager() {
        this(FilePaths.SQUID_TROL_USER_TIME_LOG, new TerminalExecute());
    }

    UsedTimeManager(String accessLogPath, TerminalExecute term) {
        this.term = term;
        this.accessLogPath = accessLogPath;
        lastLine = 0;
        lastUpdateTimestamp = LocalTime.now();
    }

    /**
     * Should be called in equal intervals by other thread or deamon,
     * reads squid logs and update users Used Time using UserDAO
     * @throws IOException problems with log file
     */
    @Scheduled(fixedDelay=60000)
    @Async
    public void checkUsersLogs() throws IOException, InterruptedException {
        if (state.equals(UsedTimeManagerState.BUSY)){
            System.out.println("nie przerywac, pracuje");
            return;
        }
        System.out.println("zaczynam prace "+ this);
        state = UsedTimeManagerState.BUSY;

        if(nextDay()) {
            log.info("Try to clear used times", dateFormat.format(new Date()));
            updateNextDay();
        }
        else {
            log.info("Try to check users logs", dateFormat.format(new Date()));
            updateUsersReadyToBlock();
        }

        System.out.println("koncze prace "+ this);
        state = UsedTimeManagerState.FREE;
    }

    private void updateNextDay() throws IOException, InterruptedException {
        clearUsedTime();
        lastLine = 0;
        term.executeCommand("squid -k rotate");
        log.info("Logi przeniesione", dateFormat.format(new Date()));
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

        usersSeconds.forEach((u,t) -> updateUserTime(u,t));

    }

    private void parseLine(Map<String,Integer> usersSeconds, String line) {
        String user = line.split("[\t ]+")[0];
        if(usersSeconds.containsKey(user))
            usersSeconds.put(user,usersSeconds.get(user)+1);
        else
            usersSeconds.put(user,1);
    }

    /**
     * Check if last update was in previous day or not
     * @return true if now returns date before lastUpdateTimestamp
     */
    private boolean nextDay() {
        return lastUpdateTimestamp.isAfter(LocalTime.now());
    }

    /**
     * Search for users entities with the same ip address like param userIp in all
     * users entities from DAO, then checks if found entity got all duration mark and
     * correct time range and then add time from second param to Used Time in users DAO
     * if any requirement is not true, then there is no update.
     *
     * @param userIp String contain 4 digits separated by commas
     * @param time got in seconds converted to minutes before placing into userDAO.
     */
    private void updateUserTime(String userIp, Integer time) {
        try {
            List<User> users = userDAO.getAllUsers();

            lastUpdateTimestamp = LocalTime.now();
            boolean changeConfiguration = false;

            log.info("Try update user: "+userIp, dateFormat.format(new Date()));

            for(User u : users) {
                if(userIp.equals(u.getUserIp())) {
                    log.info("User found in base", dateFormat.format(new Date()));
                    if(u.getHasDuration() &&
                            lastUpdateInRange(u.getTimeBegin(),u.getTimeEnd())
                            && u.getDurationInterval() > u.getUsedTime()) {
                        u.addUsedTime(time/60+1);
                        userDAO.updateUser(u);
                        log.info("User updated.", dateFormat.format(new Date()));
                        if(u.getUsedTime() == u.getDurationInterval()) {
                            log.info("User blocked.", dateFormat.format(new Date()));
                            changeConfiguration = true;
                        }
                    }
                }
            }

            if(changeConfiguration)
                fileController.saveConfiguration();

        } catch (NullPointerException e) {
            log.error("Null from userDAO", dateFormat.format(new Date()));
            e.printStackTrace();
        }
    }

    /**
     * Set Used Time of all users from userDAO to 0.
     */
    private void clearUsedTime() {
        List<User> users = userDAO.getAllUsers();
        lastUpdateTimestamp = LocalTime.now();

        for(User u : users) {
            u.setUsedTime(0);
            userDAO.updateUser(u);
        }

        fileController.saveConfiguration();
    }

    private boolean lastUpdateInRange(LocalTime start, LocalTime end) {
        return lastUpdateTimestamp.isBefore(end) && lastUpdateTimestamp.isAfter(start);
    }

}
