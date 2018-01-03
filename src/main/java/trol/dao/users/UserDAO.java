package trol.dao.users;

import trol.domain.trol_api.model.User;

public interface UserDAO {
    User getUser(int userId);
    int addUser(User user);
    void deleteUser(User user);
    void updateUser(User user);
}
