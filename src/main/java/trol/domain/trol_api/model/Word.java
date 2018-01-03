package trol.domain.trol_api.model;

import trol.domain.database_models.WordsEntity;

public class Word implements Comparable<Word>{

    private int idWord;
    private String wordString;
    private int idWordsList;

    public Word() {
    }

    public Word(WordsEntity entity) {
        idWord = entity.getIdWord();
        wordString = entity.getWordString();
        idWordsList = entity.getIdWordsList().getIdWordsList();
    }

    public void setIdWord(int idWord) {
        this.idWord = idWord;
    }

    public void setWordString(String wordString) {
        this.wordString = wordString;
    }

    public void setIdWordsList(int idWordsList) {
        this.idWordsList = idWordsList;
    }

    public int getIdWord() {
        return idWord;
    }

    public String getWordString() {
        return wordString;
    }

    public int getIdWordsList() {
        return idWordsList;
    }

    @Override
    public int compareTo(Word o) {
        return idWord - o.idWord;
    }
}
