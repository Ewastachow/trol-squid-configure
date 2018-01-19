package trol.service.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trol.dao.users.UserDAO;
import trol.model.User;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public List<User> getUsersList() {
        return userDAO.getAllUsers();
    }

    @Override
    public User getUser(int userId) {
        return userDAO.getUser(userId);
    }

    @Override
    public int addUser(User user) {
        return userDAO.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    @Override
    public void deleteUser(int userId) {
        userDAO.deleteUser(userId);
    }
}
