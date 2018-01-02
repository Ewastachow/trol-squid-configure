package trol.service.words;

import trol.model.words.Words;
import trol.model.words.WordsList;

public interface WordsListService {
    WordsList getWordsList();
    Words getWords(String listName) throws Exception;
    void addWords(Words words) throws Exception;
    void deleteWords(String listName) throws Exception;
}
