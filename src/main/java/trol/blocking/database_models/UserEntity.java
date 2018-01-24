package trol.blocking.database_models;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.time.LocalTime;

@Entity
@Table(name = "user", schema = "estacho1")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private int idUser;
    private String userIp;
    private byte isActive;
    private byte isTimed;
    private Time timeBegin;
    private Time timeEnd;
    private byte hasDuration;
    private Integer durationInterval;
    private Integer usedTime;


    public UserEntity() {
        isActive = 0;
        isTimed = 0;
        timeBegin = Time.valueOf(LocalTime.of(12,0));
        timeEnd = Time.valueOf(LocalTime.of(13,0));
        hasDuration = 0;
        durationInterval = 60;
        usedTime = 0;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "user_ip")
    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    @Basic
    @Column(name = "is_active", nullable = false, columnDefinition = "BOOLEAN FALSE")
    public byte getIsActive() {
        return isActive;
    }

    public void setIsActive(byte isActive) {
        this.isActive = isActive;
    }

    @Basic
    @Column(name = "is_timed", nullable = false, columnDefinition = "BOOLEAN FALSE")
    public byte getIsTimed() {
        return isTimed;
    }

    public void setIsTimed(byte isTimed) {
        this.isTimed = isTimed;
    }

    @Basic
    @Column(name = "time_begin", nullable = false, columnDefinition = "TIME DEFAULT '12:00'")
    public Time getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(Time timeBegin) {
        this.timeBegin = timeBegin;
    }

    @Basic
    @Column(name = "time_end", nullable = false, columnDefinition = "TIME DEFAULT '13:00'")
    public Time getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Time timeEnd) {
        this.timeEnd = timeEnd;
    }

    @Basic
    @Column(name = "has_duration", nullable = false, columnDefinition = "BOOLEAN FALSE")
    public byte getHasDuration() {
        return hasDuration;
    }

    public void setHasDuration(byte hasDuration) {
        this.hasDuration = hasDuration;
    }

    @Basic
    @Column(name = "duration_interval", nullable = false, columnDefinition = "INTEGER 60")
    public Integer getDurationInterval() {
        return durationInterval;
    }

    public void setDurationInterval(Integer durationInterval) {
        this.durationInterval = durationInterval;
    }

    @Basic
    @Column(name = "used_time", nullable = false, columnDefinition = "INTEGER 0")
    public Integer getUsedTime() {
        return usedTime;
    }

    public void setUsedTime(Integer usedTime) {
        this.usedTime = usedTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (idUser != that.idUser) return false;
        if (isActive != that.isActive) return false;
        if (isTimed != that.isTimed) return false;
        if (hasDuration != that.hasDuration) return false;
        if (userIp != null ? !userIp.equals(that.userIp) : that.userIp != null) return false;
        if (timeBegin != null ? !timeBegin.equals(that.timeBegin) : that.timeBegin != null) return false;
        if (timeEnd != null ? !timeEnd.equals(that.timeEnd) : that.timeEnd != null) return false;
        if (durationInterval != null ? !durationInterval.equals(that.durationInterval) : that.durationInterval != null)
            return false;
        if (usedTime != null ? !usedTime.equals(that.usedTime) : that.usedTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUser;
        result = 31 * result + (userIp != null ? userIp.hashCode() : 0);
        result = 31 * result + (int) isActive;
        result = 31 * result + (int) isTimed;
        result = 31 * result + (timeBegin != null ? timeBegin.hashCode() : 0);
        result = 31 * result + (timeEnd != null ? timeEnd.hashCode() : 0);
        result = 31 * result + (int) hasDuration;
        result = 31 * result + (durationInterval != null ? durationInterval.hashCode() : 0);
        result = 31 * result + (usedTime != null ? usedTime.hashCode() : 0);
        return result;
    }
}
