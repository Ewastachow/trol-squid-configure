package trol.dao.words;

import trol.model.Word;

public interface WordDAO {
    Word getWord(int wordId);
    int addWord(Word word);
    void deleteWord(Word word);
    void updateWord(Word word);
}
