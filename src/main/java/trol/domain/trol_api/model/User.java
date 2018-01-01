package trol.domain.trol_api.model;

import trol.domain.database_models.UserEntity;

import java.sql.Time;
import java.time.LocalTime;

public class User {

    private int idUser;
    private String userIp;
    private byte isActive;
    private byte isTimed;
    private LocalTime timeBegin;
    private LocalTime timeEnd;
    private byte hasDuration;
    private Integer durationInterval;
    private Integer usedTime;

    public User(UserEntity entity) {
        idUser = entity.getIdUser();
        userIp = entity.getUserIp();
        isActive = entity.getIsActive();
        isTimed = entity.getIsTimed();
        timeBegin = entity.getTimeBegin().toLocalTime();
        timeEnd = entity.getTimeEnd().toLocalTime();
        hasDuration = entity.getHasDuration();
        durationInterval = entity.getDurationInterval();
        usedTime = entity.getUsedTime();
    }

    public int getIdUser() {
        return idUser;
    }

    public String getUserIp() {
        return userIp;
    }

    public byte getIsActive() {
        return isActive;
    }

    public byte getIsTimed() {
        return isTimed;
    }

    public LocalTime getTimeBegin() {
        return timeBegin;
    }

    public LocalTime getTimeEnd() {
        return timeEnd;
    }

    public byte getHasDuration() {
        return hasDuration;
    }

    public Integer getDurationInterval() {
        return durationInterval;
    }

    public Integer getUsedTime() {
        return usedTime;
    }
}
