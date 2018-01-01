package trol.domain.trol_api.model;

import trol.domain.database_models.WordsListsEntity;

import java.sql.Time;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class WordsList {
    private int idWordsList;
    private String wordsListName;
    private byte isActive;
    private byte isTimed;
    private LocalTime timeBegin;
    private LocalTime timeEnd;
    private Set<Word> wordsSet;

    public WordsList(WordsListsEntity entity) {
        wordsSet = new HashSet<>();
        idWordsList = entity.getIdWordsList();
        wordsListName = entity.getWordsListName();
        isActive = entity.getIsActive();
        isTimed = entity.getIsTimed();
        timeBegin = entity.getTimeBegin().toLocalTime();
        timeEnd = entity.getTimeEnd().toLocalTime();
        entity.getWordsEntitySet().forEach(e -> wordsSet.add(new Word(e)));
    }

    public int getIdWordsList() {
        return idWordsList;
    }

    public String getWordsListName() {
        return wordsListName;
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

    public Set<Word> getWordsSet() {
        return wordsSet;
    }
}
