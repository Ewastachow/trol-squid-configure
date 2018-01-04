package trol.domain.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import trol.dao.users.UserDAOImpl;
import trol.domain.filemanager.FileController;
import trol.domain.trol_api.model.User;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class BlockTimeManager {

    @Autowired
    private FileController fileController;
    @Autowired
    private UserDAOImpl userDAO;

    private static final Logger log = LoggerFactory.getLogger(LogsReader.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private LocalTime lastUpdateTimestamp;

    BlockTimeManager() {
        lastUpdateTimestamp = LocalTime.now();
    }

    /**
     * Check if last update was in previous day or not
     * @return true if now returns date before lastUpdateTimestamp
     */
    public boolean nextDay() {
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
    public void updateUserTime(String userIp, Integer time) {
        try {
            List<User> users = userDAO.getAllUsers();

            lastUpdateTimestamp = LocalTime.now();
            boolean updated = false;

            log.info("Try update user: "+userIp, dateFormat.format(new Date()));

            for(User u : users) {
                if(userIp.equals(u.getUserIp())) {
                    if(u.getHasDuration() &&
                            lastUpdateInRange(u.getTimeBegin(),u.getTimeEnd())) {
                        u.addUsedTime(time/60);
                        userDAO.updateUser(u);
                        updated = true;
                        log.info("User updated.", dateFormat.format(new Date()));
                    }
                }
            }

            if(updated)
                fileController.saveConfiguration();

        } catch (NullPointerException e) {
            log.error("Null from userDAO", dateFormat.format(new Date()));
        }
    }

    /**
     * Set Used Time of all users from userDAO to 0.
     */
    public void clearUsedTime() {
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
