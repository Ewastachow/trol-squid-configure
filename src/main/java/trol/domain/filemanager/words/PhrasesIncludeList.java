package trol.domain.filemanager.words;

import trol.domain.filemanager.FilePaths;
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
        path = Paths.get(FilePaths.PHRASE_INCLUDE_LIST_PATH);
    }

    public PhrasesIncludeList(Path path, List<WordsList> wordsListList) {
        this.path = path;
        this.wordsListList = wordsListList;
    }

    public void saveFile() throws IOException {
        FileHelper.saveStringListAsFile(path, generateFileListstring());
    }

    public List<String> generateFileListstring(){
        List<String> wordsFile = new ArrayList<>();
        wordsListList.forEach(e -> {
            if (e.getIsActive())
                wordsFile.add(".Include<" + FilePaths.PHRASE_LISTS_PATH + e.getWordsListName() + ">");
        });
        return wordsFile;
    }
}
