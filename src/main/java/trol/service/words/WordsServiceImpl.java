package trol.service.words;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trol.exceptions.DomainsListUpdateException;
import trol.model.words.Words;

import java.util.List;

@Service("wordsService")
public class WordsServiceImpl implements WordsService {
    @Autowired
    private WordsListService wordsListService;

    @Override
    public void editListHeader(Words newList) throws RuntimeException {
        try {
            Words oldList = wordsListService.getWords(newList.getName());
            List<Words> wordsLists = wordsListService
                    .getWordsList()
                    .getWordsList();
            wordsLists.set(
                    wordsLists.indexOf(oldList),
                    newList
            );
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void editWordInList(String listName, String oldWord, String newWord) throws Exception {
        try {
            Words list = wordsListService.getWords(listName);
            list.getWordsList()
                    .set(
                            list.getWordsList().indexOf(oldWord),
                            newWord
                    );
        } catch (Exception e) {
            throw new DomainsListUpdateException();
        }
    }

    @Override
    public void deleteWordInList(String listName, String word) throws DomainsListUpdateException {
        try {
            wordsListService.getWords(listName)
                    .getWordsList()
                    .remove(word);
        } catch (Exception e) {
            throw new DomainsListUpdateException();
        }
    }

    @Override
    public void addWordToList(String listName, String word) throws DomainsListUpdateException {
        try {
            wordsListService.getWords(listName)
                    .getWordsList()
                    .add(word);
        } catch (Exception e) {
            throw new DomainsListUpdateException();
        }
    }
}
