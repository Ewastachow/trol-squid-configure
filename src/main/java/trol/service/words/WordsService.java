package trol.service.words;

import trol.domain.trol_api.exception.UnsuccessfulDeletException;
import trol.domain.trol_api.exception.UnsuccessfulModificationException;
import trol.domain.trol_api.model.Word;
import trol.domain.trol_api.model.WordsList;

import java.util.List;

public interface WordsService {
    List<WordsList> getWordsLists();
    WordsList getWordsList(int wordsListId);
    int addWordsList(WordsList wordsList) throws UnsuccessfulModificationException;
    void updateWordsListProperties(WordsList wordsList) throws UnsuccessfulModificationException;
    void deleteWordsList(int wordsListId) throws UnsuccessfulDeletException;

    Word getWord(int wordId);
    int addWordToWordsList(Word word);
    void updateWordInList(Word word) throws UnsuccessfulModificationException;
    void deleteWord(Word word) throws UnsuccessfulDeletException;
}
