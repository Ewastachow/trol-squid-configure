package trol.domain.filemanager.dansguardian.words;

import trol.domain.filemanager.FilePaths;
import trol.domain.util.FileHelper;
import trol.model.WordsList;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

class PhrasesIncludeList {
    private Path path;
    private List<WordsList> wordsListList;

    PhrasesIncludeList(List<WordsList> wordsListList) {
        this.wordsListList = wordsListList;
        path = Paths.get(FilePaths.DANSGUARDIAN_PHRASE_INCLUDE_LIST_PATH);
    }

    void saveFile() throws IOException {
        FileHelper.saveStringListAsFile(path, generateFileListstring());
    }

    private List<String> generateFileListstring(){
        List<String> wordsFile = new ArrayList<>();
        wordsListList.forEach(e -> {
            if (e.getIsActive())
                wordsFile.add(".Include<" + FilePaths.DANSGUARDIAN_PHRASE_LISTS_PATH + e.getWordsListName().toLowerCase() + e.getIdWordsList() + ">");
        });
        return wordsFile;
    }
}
