package trol.domain.database_models;

import javax.persistence.*;

@Entity
@Table(name = "words")
public class Words {

    private Long idWord;
    private String wordString;
    private WordsLists idWordsList;

    public Words() {
    }

    public Words(Long idWord, String wordString, WordsLists idWordsList) {
        this.idWord = idWord;
        this.wordString = wordString;
        this.idWordsList = idWordsList;
    }

    @Id
    @GeneratedValue
    @Column(name = "id_words")
    public Long getIdWord() {
        return idWord;
    }

    public void setIdWord(Long idWord) {
        this.idWord = idWord;
    }

    @Column(name = "word_string", nullable = false, length = 30)
    public String getWordString() {
        return wordString;
    }

    public void setWordString(String wordString) {
        this.wordString = wordString;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    public WordsLists getIdWordsList() {
        return idWordsList;
    }

    public void setIdWordsList(WordsLists idWordsList) {
        this.idWordsList = idWordsList;
    }
}

