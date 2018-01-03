package trol.domain.log;

import org.springframework.beans.factory.annotation.Autowired;
import trol.dao.users.UserDAOImpl;
import trol.domain.filemanager.FileController;
import trol.domain.trol_api.model.User;

import java.time.LocalTime;
import java.util.List;

public class BlockTimeManager {

    @Autowired
    private FileController fileController;
    @Autowired
    private UserDAOImpl userDAO;

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

    public void updateUserTime(String userIp, Integer time) {
        List<User> users = userDAO.getAllUsers();

        for(User u : users) {
            if(userIp.equals(u.getUserIp())) {
                if(u.getHasDuration()) {
                    u.addUsedTime(time);
                    userDAO.updateUser(u);
                    fileController.saveConfiguration();
                }
            }
        }
    }

    public void clearWastedTime() {
        List<User> users = userDAO.getAllUsers();

        for(User u : users) {
            u.setUsedTime(0);
            userDAO.updateUser(u);
            fileController.saveConfiguration();
        }
    }
}
