package trol.domain.database_models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "words", schema = "estacho1")
public class WordsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private int idWord;
    private String wordString;
    private WordsListsEntity idWordsList;

    public WordsEntity(String wordString, WordsListsEntity idWordsList) {
        this.wordString = wordString;
        this.idWordsList = idWordsList;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne
    @JoinColumn(name="id_words_list", nullable=false)
    //@Column(name = "id_words_list")
    public WordsListsEntity getIdWordsList() {
        return idWordsList;
    }

    public void setIdWordsList(WordsListsEntity idWordsList) {
        this.idWordsList = idWordsList;
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
}
