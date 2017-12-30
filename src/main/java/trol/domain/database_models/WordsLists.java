package trol.domain.database_models;

import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

public class WordsLists {
    private Long idWordsList;
    private String wordsListName;
    private Boolean isActive;
    private Boolean isTimed;
    private Time timeBegin;
    private Time timeEnd;

    private Set<Words> wordsSet = new HashSet<>();

    public Long getIdWordsList() {
        return idWordsList;
    }

    public void setIdWordsList(Long idWordsList) {
        this.idWordsList = idWordsList;
    }

    public String getWordsListName() {
        return wordsListName;
    }

    public void setWordsListName(String wordsListName) {
        this.wordsListName = wordsListName;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getIsTimed() {
        return isTimed;
    }

    public void setTimed(Boolean timed) {
        isTimed = timed;
    }

    public Time getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(Time timeBegin) {
        this.timeBegin = timeBegin;
    }

    public Time getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Time timeEnd) {
        this.timeEnd = timeEnd;
    }

    public Set<Words> getWordsSet() {
        return wordsSet;
    }

    public void setWordsSet(Set<Words> wordsSet) {
        this.wordsSet = wordsSet;
    }
}
