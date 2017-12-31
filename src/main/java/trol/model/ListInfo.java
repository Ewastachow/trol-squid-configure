package trol.model;

import javax.validation.constraints.Size;
import java.time.LocalTime;

public class ListInfo {
    private long id;
    @Size(min = 1)
    private String name;
    private boolean isActive;
    private boolean isTimed;
    private LocalTime timeBegin;
    private LocalTime timeEnd;

    public ListInfo(){
        id = 0;
        isActive = false;
        isTimed = false;
        timeBegin = LocalTime.MIN;
        timeEnd = LocalTime.MAX;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean getisTimed() {
        return isTimed;
    }

    public void setIsTimed(boolean isTimed) {
        this.isTimed = isTimed;
    }

    public LocalTime getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(LocalTime timeBegin) {
        this.timeBegin = timeBegin;
    }

    public LocalTime getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(LocalTime timeEnd) {
        this.timeEnd = timeEnd;
    }
}
