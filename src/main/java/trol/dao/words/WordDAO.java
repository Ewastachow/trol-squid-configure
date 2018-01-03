package trol.dao.words;

import trol.domain.trol_api.model.Word;

public interface WordDAO {
    Word getWord(int wordId);
    int addWord(Word word);
    void deleteWord(Word word);
    void updateWord(Word word);
}
