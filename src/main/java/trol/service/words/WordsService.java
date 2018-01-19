package trol.service.words;

import trol.model.Word;
import trol.model.WordsList;

import java.util.List;

public interface WordsService {
    List<WordsList> getWordsLists();
    WordsList getWordsList(int wordsListId);
    int addWordsList(WordsList wordsList);
    void updateWordsListProperties(WordsList wordsList);
    void deleteWordsList(int wordsListId);

    Word getWord(int wordId);
    int addWordToWordsList(Word word);
    void updateWordInList(Word word);
    void deleteWord(Word word);
}
