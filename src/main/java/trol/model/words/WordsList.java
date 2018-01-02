package trol.model.words;

import java.util.ArrayList;
import java.util.List;

public class WordsList {
    private List<Words> wordsList;

    public WordsList(){
        wordsList = new ArrayList<>();
    }

    public List<Words> getWordsList() {
        return wordsList;
    }

    public void setWordsList(List<Words> wordsList) {
        this.wordsList = wordsList;
    }
}
