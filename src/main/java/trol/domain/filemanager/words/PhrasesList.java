package trol.domain.filemanager.words;

import trol.domain.filemanager.FilePaths;
import trol.domain.trol_api.model.WordsList;
import trol.domain.util.FileHelper;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PhrasesList {
    private Path path;
    private WordsList wordsList;

    public PhrasesList(WordsList wordsList) {
        this.wordsList = wordsList;
        path = Paths.get(FilePaths.PHRASE_LISTS_PATH+wordsList.getWordsListName());
    }

    public PhrasesList(Path path, WordsList wordsList) {
        this.path = path;
        this.wordsList = wordsList;
    }

    public void saveFile() throws IOException {
        if(!wordsList.getIsActive()) return;
        FileHelper.saveStringListAsFile(path,generateFileListstring());
    }

    public List<String> generateFileListstring(){
        List<String> phrasesFile = new ArrayList<>();
        phrasesFile.add(FileHelper.dansguardianTimeControlLine(wordsList.getTimeBegin(), wordsList.getTimeEnd()));
        wordsList.getWordsSet().forEach(e -> phrasesFile.add(wordLine(e.getWordString())));
        return phrasesFile;
    }

    private String wordLine(String word){
        return "<"+word+">";
    }
}
