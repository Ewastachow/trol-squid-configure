package trol.service.words;

import trol.model.words.Words;

public interface WordsService {
    void editListHeader(Words newList) throws RuntimeException;
    void editWordInList(String listName, String oldWord, String newWord) throws Exception;
    void deleteWordInList(String listName, String word) throws Exception;
    void addWordToList(String listName, String word) throws Exception;
}
