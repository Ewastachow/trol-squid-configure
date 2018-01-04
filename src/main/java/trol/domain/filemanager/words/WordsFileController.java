package trol.domain.filemanager.words;

import trol.domain.trol_api.model.WordsList;

import java.io.IOException;
import java.util.List;

public class WordsFileController {

    public static void saveWordsFile(List<WordsList> wordsListList) throws IOException {
        PhrasesIncludeList phrasesIncludeList = new PhrasesIncludeList(wordsListList);
        phrasesIncludeList.saveFile();
        for(WordsList i: wordsListList){
            if(i.getIsActive()){
                PhrasesList phrasesList = new PhrasesList(i);
                phrasesList.saveFile();
            }
        }
    }
}
