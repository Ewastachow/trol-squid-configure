package trol.model.words;

import javax.validation.constraints.Size;

public class WordInList {
    @Size(min = 1)
    private String listName;

    @Size(min = 1)
    private String word;

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
