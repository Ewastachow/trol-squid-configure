package trol.dao.appusers;

import trol.model.AppUser;

public interface AppUsersDAO {
    AppUser getAppUserByName(String name);
    void updateAppUserPassword(String username, String password);
}
