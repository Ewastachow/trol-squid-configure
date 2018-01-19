package trol.domain.filemanager.dansguardian.words;

import trol.model.WordsList;

import java.io.IOException;
import java.util.List;

public class WordsFileController {

    /**
     * Creates Files for blocked phrases and include them into bannedphraselist in dansguardian configuration list
     * @param wordsListList
     * @throws IOException
     */
    public static void saveFile(List<WordsList> wordsListList) throws IOException {
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
