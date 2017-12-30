package trol.model.DomainsList;

import trol.domain.trol_api.domain.Mode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DomainsList {
    @NotNull
    //@Uniqueintrollblabla //TODO sprawdzenie czy jest unikalne
    @Size(min = 1, max = 100)
    public String name;
    public boolean isTimed;
    public LocalTime timeBegin;
    public LocalTime timeEnd;
    @NotNull
    public Mode mode;
    public List<String> domainList;

    public DomainsList(){
        isTimed = false;
        timeBegin = LocalTime.MIN;
        timeEnd = LocalTime.MAX;
        mode = Mode.INACTIVE;
        domainList = new ArrayList<>();
    }

    public DomainsList(String name){
        this();
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getisTimed() {
        return isTimed;
    }

    public void setIsTimed(boolean timed) {
        isTimed = timed;
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

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public List<String> getDomainList() {
        return domainList;
    }

    public void setDomainList(List<String> domainList) {
        this.domainList = domainList;
    }
}
