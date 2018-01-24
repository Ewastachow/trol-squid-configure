package trol.model;

import trol.blocking.database_models.AppUserEntity;

public class AppUser {
    private String username;
    private String password;
    private boolean enabled;

    public AppUser(AppUserEntity entity){
        username = entity.getUsername();
        password = entity.getPassword();
        enabled = entity.isEnabled();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
