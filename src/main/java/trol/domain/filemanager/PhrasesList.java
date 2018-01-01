package trol.domain.filemanager;

import trol.domain.trol_api.model.WordsList;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PhrasesList {
    Path path;
    WordsList wordsList;

    public PhrasesList(WordsList wordsList) {
        path = Paths.get(FilePaths.WORDS_LISTS_PATH+wordsList.getWordsListName());
        this.wordsList = wordsList;
    }

    public PhrasesList(Path path, WordsList wordsList) {
        this.path = path;
        this.wordsList = wordsList;
    }

    public void saveFile(){
        //TODO Implement
    }
}
