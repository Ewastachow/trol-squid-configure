package trol.domain.filemanager;

import trol.domain.trol_api.model.WordsList;
import trol.domain.util.FileHelper;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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

    public void saveFile() throws IOException {
        List<String> wordsFile = new ArrayList<>();
        wordsListList.forEach(e -> wordsFile.add(".Include<"+FilePaths.WORDS_LISTS_PATH+e.getWordsListName()+">"));
        FileHelper.saveStringListAsFile(path,wordsFile);
    }
}
