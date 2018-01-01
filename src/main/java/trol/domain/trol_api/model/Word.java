package trol.domain.trol_api.model;

import trol.domain.database_models.WordsEntity;

public class Word {

    private int idWord;
    private String wordString;
    private int idWordsList;

    public Word(WordsEntity entity) {
        idWord = entity.getIdWord();
        wordString = entity.getWordString();
        idWordsList = entity.getIdWordsList().getIdWordsList();
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
}
