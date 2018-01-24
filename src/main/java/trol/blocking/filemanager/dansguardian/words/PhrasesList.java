package trol.blocking.filemanager.dansguardian.words;

import trol.blocking.filemanager.FilePaths;
import trol.blocking.util.FileHelper;
import trol.model.WordsList;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

class PhrasesList {
    private Path path;
    private WordsList wordsList;

    PhrasesList(WordsList wordsList) {
        this.wordsList = wordsList;
        path = Paths.get(FilePaths.DANSGUARDIAN_PHRASE_LISTS_PATH +wordsList.getWordsListName().toLowerCase() + wordsList.getIdWordsList());
    }

    void saveFile() throws IOException {
        if(!wordsList.getIsActive()) return;
        FileHelper.saveStringListAsFile(path,generateFileListstring());
    }

    private List<String> generateFileListstring(){
        List<String> phrasesFile = new ArrayList<>();
        if(wordsList.getIsTimed())
            phrasesFile.add(FileHelper.dansguardianTimeControlLine(wordsList.getTimeBegin(), wordsList.getTimeEnd()));
        wordsList.getWordsSet().forEach(e -> phrasesFile.add(wordLine(e.getWordString())));
        return phrasesFile;
    }

    private String wordLine(String word){
        return "<"+ word +">";
    } 
}
