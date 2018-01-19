package trol.dao.users;

import trol.model.User;

import java.util.List;

public interface UserDAO {
    List<User> getAllUsers();
    User getUser(int userId);
    int addUser(User user);
    void deleteUser(int userId);
    void updateUser(User user);
}
