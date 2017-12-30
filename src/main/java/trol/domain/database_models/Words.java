package trol.domain.database_models;

public class Words {

    private Long idWord;
    private String wordString;
    private Long idWordsList;

    public Long getIdWord() {
        return idWord;
    }

    public void setIdWord(Long idWord) {
        this.idWord = idWord;
    }

    public String getWordString() {
        return wordString;
    }

    public void setWordString(String wordString) {
        this.wordString = wordString;
    }

    public Long getIdWordsList() {
        return idWordsList;
    }

    public void setIdWordsList(Long idWordsList) {
        this.idWordsList = idWordsList;
    }
}
