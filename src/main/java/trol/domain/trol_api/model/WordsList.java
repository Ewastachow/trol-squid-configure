package trol.domain.trol_api.model;

import org.hibernate.validator.constraints.Length;
import trol.domain.database_models.WordsListsEntity;

import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class WordsList {
    private int idWordsList;
    @Length(min = 1, max = 100, message = "Length must between 1 and 100")
    private String wordsListName;
    private boolean isActive;
    private boolean isTimed;
    @NotNull(message = "Must not be null")
    private LocalTime timeBegin;
    @NotNull(message = "Must not be null")
    private LocalTime timeEnd;
    private Set<Word> wordsSet;

    public WordsList() {
        isActive = false;
        isTimed = false;
        timeBegin = LocalTime.MIN;
        timeEnd = LocalTime.MAX;
        wordsSet = new TreeSet<>();
    }

    public WordsList(WordsListsEntity entity) {
        wordsSet = new TreeSet<>();
        idWordsList = entity.getIdWordsList();
        wordsListName = entity.getWordsListName();
        isActive = entity.getIsActive() == 1;
        isTimed = entity.getIsTimed() == 1;
        timeBegin = entity.getTimeBegin().toLocalTime();
        timeEnd = entity.getTimeEnd().toLocalTime();
        entity.getWordsEntitySet().forEach(e -> wordsSet.add(new Word(e)));
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void setIsTimed(boolean isTimed) {
        this.isTimed = isTimed;
    }

    public void setWordsListName(String wordsListName) {
        this.wordsListName = wordsListName;
    }

    public void setTimeBegin(LocalTime timeBegin) {
        this.timeBegin = timeBegin;
    }

    public void setTimeEnd(LocalTime timeEnd) {
        this.timeEnd = timeEnd;
    }

    public void setWordsSet(Set<Word> wordsSet) {
        this.wordsSet = wordsSet;
    }

    public void setIdWordsList(int idWordsList) {
        this.idWordsList = idWordsList;
    }

    public int getIdWordsList() {
        return idWordsList;
    }

    public String getWordsListName() {
        return wordsListName;
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

    public Set<Word> getWordsSet() {
        return wordsSet;
    }


}
