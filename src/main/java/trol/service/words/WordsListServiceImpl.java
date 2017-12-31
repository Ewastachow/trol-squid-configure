package trol.service.words;

import org.springframework.stereotype.Service;
import trol.exceptions.ListNameException;
import trol.model.words.Words;
import trol.model.words.WordsList;

import java.util.Optional;

@Service("wordsListService")
public class WordsListServiceImpl implements WordsListService {
    private static WordsList wordsList = new WordsList();

    @Override
    public WordsList getWordsList() {
        return wordsList;
    }

    @Override
    public void addWords(Words words) throws Exception {
        boolean isInList = wordsList.getWordsList().stream()
                .anyMatch(d -> d.getName().equals(words.getName()));

        if (isInList) throw new ListNameException("List with that name already exists!");
        wordsList.getWordsList().add(words);
    }

    @Override
    public Words getWords(String listName) throws Exception {
        boolean isInList = wordsList.getWordsList().stream()
                .anyMatch(d -> d.getName().equals(listName));
        if (!isInList) throw new ListNameException("List with that name cannot be found!");
        return wordsList.getWordsList().stream()
                .filter(d -> d.getName().equals(listName))
                .findFirst()
                .get();
    }

    @Override
    public void deleteWords(String listName) throws Exception {
        Optional<Words> listOptional = wordsList.getWordsList().stream()
                .filter(d -> d.getName().equals(listName))
                .findFirst();

        if (!listOptional.isPresent()) throw new ListNameException("List with that name does not exist!");
        wordsList.getWordsList().remove(listOptional.get());
    }
}
