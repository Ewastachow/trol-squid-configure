package trol.domain.filemanager;

import trol.domain.trol_api.model.WordsList;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class PhrasesIncludeList {
    Path path;
    List<WordsList> wordsListList;

    public PhrasesIncludeList(List<WordsList> wordsListList) {
        this.wordsListList = wordsListList;
        path = Paths.get(FilePaths.WORDS_INCLUDE_LIST_PATH);
    }

    public PhrasesIncludeList(Path path, List<WordsList> wordsListList) {
        this.path = path;
        this.wordsListList = wordsListList;
    }

    public void saveFile(){
        //TODO Implement
    }
}
