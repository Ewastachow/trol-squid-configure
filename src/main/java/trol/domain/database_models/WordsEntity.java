package trol.domain.database_models;

import javax.persistence.*;

@Entity
@Table(name = "words", schema = "estacho1")
public class WordsEntity {
    private int idWord;
    private String wordString;
    private int idWordsList;

    @Id
    @Column(name = "id_word")
    public int getIdWord() {
        return idWord;
    }

    public void setIdWord(int idWord) {
        this.idWord = idWord;
    }

    @Basic
    @Column(name = "word_string")
    public String getWordString() {
        return wordString;
    }

    public void setWordString(String wordString) {
        this.wordString = wordString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WordsEntity that = (WordsEntity) o;

        if (idWord != that.idWord) return false;
        if (wordString != null ? !wordString.equals(that.wordString) : that.wordString != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idWord;
        result = 31 * result + (wordString != null ? wordString.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "id_words_list")
    public int getIdWordsList() {
        return idWordsList;
    }

    public void setIdWordsList(int idWordsList) {
        this.idWordsList = idWordsList;
    }
}
