package trol.domain.filemanager;

import trol.domain.trol_api.model.DomainsList;
import trol.domain.trol_api.model.WordsList;

import java.io.IOException;
import java.util.List;

public class FileController {

    public static void saveWordsListsToFile(List<WordsList> wordsListList){
        wordsListList.forEach(e -> {
            PhrasesList phrasesList = new PhrasesList(e);
            //TODO zucic wyjatek
            try {
                phrasesList.saveFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }

    public static void saveWordsIncludeListToFile(List<WordsList> wordsListList){
        PhrasesIncludeList phrasesIncludeList = new PhrasesIncludeList(wordsListList);
        try {
            phrasesIncludeList.saveFile();
        } catch (IOException e) {
            //TODO zucic wyjatek
            e.printStackTrace();
        }
    }

    public static void saveDomainsBlocadeFile(List<DomainsList> domainsListList){
        domainsListList.forEach(e -> {
            DomainList domainList = new DomainList(e);
            try {
                domainList.saveFile();
            } catch (IOException e1) {
                //TODO zucic wyjatek
                e1.printStackTrace();
            }
        });

    }
}
