package trol.domain.database_models;

import javax.persistence.*;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "words_lists")
public class WordsLists {
    private Long idWordsList;
    private String wordsListName;
    private Boolean isActive;
    private Boolean isTimed;
    private Time timeBegin;
    private Time timeEnd;

//    private Set<Words> wordsSet = new HashSet<>();

    @Id
    @GeneratedValue
    @Column(name = "id_words_list")
    public Long getIdWordsList() {
        return idWordsList;
    }

    public void setIdWordsList(Long idWordsList) {
        this.idWordsList = idWordsList;
    }

    @Column(name = "words_list_name", nullable = false, length = 30)
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

//    public Set<Words> getWordsSet() {
//        return wordsSet;
//    }
//
//    public void setWordsSet(Set<Words> wordsSet) {
//        this.wordsSet = wordsSet;
//    }
}
