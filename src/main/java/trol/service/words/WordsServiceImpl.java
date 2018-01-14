package trol.service.words;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trol.dao.words.WordDAO;
import trol.dao.words.WordsListDAO;
import trol.domain.trol_api.exception.UnsuccessfulDeletException;
import trol.domain.trol_api.exception.UnsuccessfulModificationException;
import trol.domain.trol_api.model.Word;
import trol.domain.trol_api.model.WordsList;

import java.util.List;

@Service("wordsService")
public class WordsServiceImpl implements WordsService {
    @Autowired
    private WordDAO wordDAO;

    @Autowired
    private WordsListDAO wordsListDAO;

    @Override
    public List<WordsList> getWordsLists() {
        return wordsListDAO.getAllWordsLists();
    }

    @Override
    public WordsList getWordsList(int wordsListId) {
        return wordsListDAO.getWordsList(wordsListId);
    }

    @Override
    public int addWordsList(WordsList wordsList) throws UnsuccessfulModificationException {
        int id = wordsListDAO.addWordsList(wordsList.getWordsListName());
        wordsList.setIdWordsList(id);
        wordsListDAO.updateWordsListProperties(wordsList);
        return id;
    }

    @Override
    public void updateWordsListProperties(WordsList wordsList) throws UnsuccessfulModificationException {
        wordsListDAO.updateWordsListProperties(wordsList);
    }

    @Override
    public void deleteWordsList(int wordsListId) throws UnsuccessfulDeletException {
        wordsListDAO.deleteWordsList(wordsListId);
    }

    @Override
    public Word getWord(int wordId) {
        return wordDAO.getWord(wordId);
    }

    @Override
    public int addWordToWordsList(Word word) {
        return wordDAO.addWord(word);
    }

    @Override
    public void updateWordInList(Word word) throws UnsuccessfulModificationException {
        wordDAO.updateWord(word);
    }

    @Override
    public void deleteWord(Word word) throws UnsuccessfulDeletException {
        wordDAO.deleteWord(word);
    }
}
