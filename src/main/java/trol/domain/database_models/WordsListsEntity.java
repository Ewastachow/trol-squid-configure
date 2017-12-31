package trol.domain.database_models;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "words_lists", schema = "estacho1")
public class WordsListsEntity {
    private int idWordsList;
    private String wordsListName;
    private byte isActive;
    private byte isTimed;
    private Time timeBegin;
    private Time timeEnd;

    @Id
    @Column(name = "id_words_list")
    public int getIdWordsList() {
        return idWordsList;
    }

    public void setIdWordsList(int idWordsList) {
        this.idWordsList = idWordsList;
    }

    @Basic
    @Column(name = "words_list_name")
    public String getWordsListName() {
        return wordsListName;
    }

    public void setWordsListName(String wordsListName) {
        this.wordsListName = wordsListName;
    }

    @Basic
    @Column(name = "is_active")
    public byte getIsActive() {
        return isActive;
    }

    public void setIsActive(byte isActive) {
        this.isActive = isActive;
    }

    @Basic
    @Column(name = "is_timed")
    public byte getIsTimed() {
        return isTimed;
    }

    public void setIsTimed(byte isTimed) {
        this.isTimed = isTimed;
    }

    @Basic
    @Column(name = "time_begin")
    public Time getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(Time timeBegin) {
        this.timeBegin = timeBegin;
    }

    @Basic
    @Column(name = "time_end")
    public Time getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Time timeEnd) {
        this.timeEnd = timeEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WordsListsEntity that = (WordsListsEntity) o;

        if (idWordsList != that.idWordsList) return false;
        if (isActive != that.isActive) return false;
        if (isTimed != that.isTimed) return false;
        if (wordsListName != null ? !wordsListName.equals(that.wordsListName) : that.wordsListName != null)
            return false;
        if (timeBegin != null ? !timeBegin.equals(that.timeBegin) : that.timeBegin != null) return false;
        if (timeEnd != null ? !timeEnd.equals(that.timeEnd) : that.timeEnd != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idWordsList;
        result = 31 * result + (wordsListName != null ? wordsListName.hashCode() : 0);
        result = 31 * result + (int) isActive;
        result = 31 * result + (int) isTimed;
        result = 31 * result + (timeBegin != null ? timeBegin.hashCode() : 0);
        result = 31 * result + (timeEnd != null ? timeEnd.hashCode() : 0);
        return result;
    }
}
