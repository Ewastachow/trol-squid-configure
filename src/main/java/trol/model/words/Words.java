package trol.model.words;

import javax.validation.constraints.Size;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Words {
    @Size(min = 1)
    private String name;
    private boolean isTimed;
    private LocalTime timeBegin;
    private LocalTime timeEnd;
    private boolean isActive;
    private List<String> wordsList;

    public Words(){
        isTimed = false;
        timeBegin = LocalTime.MIN;
        timeEnd = LocalTime.MAX;
        isActive = false;
        wordsList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsTimed() {
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

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public List<String> getWordsList() {
        return wordsList;
    }

    public void setWordsList(List<String> wordsList) {
        this.wordsList = wordsList;
    }
}
