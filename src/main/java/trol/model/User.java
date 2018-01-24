package trol.model;

import trol.blocking.database_models.UserEntity;
import trol.validation.IPv4;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

public class User implements Comparable<User> {

    private int idUser;
    @IPv4
    private String userIp;
    private boolean isActive;
    private boolean isTimed;
    @NotNull(message = "Must not be null")
    private LocalTime timeBegin;
    @NotNull(message = "Must not be null")
    private LocalTime timeEnd;
    private boolean hasDuration;
    @NotNull(message = "May not be null")
    @Min(value = 0,message = "Must be greater than 0")
    private Integer durationInterval;
    private Integer usedTime;

    public User() {
        isActive = false;
        isTimed = false;
        timeBegin = LocalTime.MIN;
        timeEnd = LocalTime.MAX;
        userIp = "";
        hasDuration = false;
        durationInterval = new Integer(0);
        usedTime = new Integer(0);

    }

    public User(UserEntity entity) {
        idUser = entity.getIdUser();
        userIp = entity.getUserIp();
        isActive = entity.getIsActive() == 1;
        isTimed = entity.getIsTimed() == 1;
        timeBegin = entity.getTimeBegin().toLocalTime();
        timeEnd = entity.getTimeEnd().toLocalTime();
        hasDuration = entity.getHasDuration() == 1;
        durationInterval = entity.getDurationInterval();
        usedTime = entity.getUsedTime();
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public void setTimeBegin(LocalTime timeBegin) {
        this.timeBegin = timeBegin;
    }

    public void setTimeEnd(LocalTime timeEnd) {
        this.timeEnd = timeEnd;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void setIsTimed(boolean isTimed) {
        this.isTimed = isTimed;
    }

    public void setHasDuration(boolean hasDuration) {
        this.hasDuration = hasDuration;
    }

    public void setDurationInterval(Integer durationInterval) {
        this.durationInterval = durationInterval;
    }

    public void setUsedTime(Integer usedTime) {
        if(usedTime > durationInterval)
            this.usedTime = durationInterval;
        else if(usedTime < 0)
            this.usedTime = 0;
        else
            this.usedTime = usedTime;
    }

    public boolean checkIfShouldBlock(Integer additionalUsedTime) {
        return this.usedTime + additionalUsedTime >= this.durationInterval;
    }

    public void addUsedTime(Integer additionalUsedTime) {
        if(this.usedTime + additionalUsedTime > durationInterval)
            this.usedTime = durationInterval;
        else if(this.usedTime + additionalUsedTime < 0)
            this.usedTime = 0;
        else
            this.usedTime += additionalUsedTime;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getUserIp() {
        return userIp;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public boolean getIsTimed() {
        return isTimed;
    }

    public LocalTime getTimeBegin() {
        return timeBegin;
    }

    public LocalTime getTimeEnd() {
        return timeEnd;
    }

    public boolean getHasDuration() {
        return hasDuration;
    }

    public Integer getDurationInterval() {
        return durationInterval;
    }

    public Integer getUsedTime() {
        return usedTime;
    }

    @Override
    public int compareTo(User o) {
        return idUser - o.idUser;
    }
}
