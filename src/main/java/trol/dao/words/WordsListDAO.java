package trol.dao.words;

import trol.model.WordsList;

import java.util.List;

public interface WordsListDAO {
    List<WordsList> getAllWordsLists();
    WordsList getWordsList(int wordsListId);
    int addWordsList(String wordsListName);
    void deleteWordsList(int wordsListId);
    void updateWordsListProperties(WordsList wordsList);
}
