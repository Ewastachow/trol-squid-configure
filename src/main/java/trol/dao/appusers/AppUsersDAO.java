package trol.dao.appusers;

import trol.domain.trol_api.model.AppUser;

public interface AppUsersDAO {
    AppUser getAppUserByName(String name);
    void updateAppUserPassword(String username, String password);
}
