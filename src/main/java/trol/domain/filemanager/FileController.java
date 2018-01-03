package trol.domain.filemanager;

import org.springframework.context.annotation.Scope;
import trol.domain.filemanager.trash.DomainList;
import trol.domain.filemanager.words.PhrasesIncludeList;
import trol.domain.filemanager.words.PhrasesList;
import trol.domain.trol_api.model.DomainsList;
import trol.domain.trol_api.model.WordsList;

import java.io.IOException;
import java.util.List;

@Scope("singleton")
public class FileController {
    SaveState state;

    public SaveState getState() {
        return state;
    }

    public FileController() {
        if(state==null)
            this.state = SaveState.FREE;
    }

    public void saveConfiguration(){

    }



    private void saveWordsListsToFile(List<WordsList> wordsListList){
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

    private void saveWordsIncludeListToFile(List<WordsList> wordsListList){
        PhrasesIncludeList phrasesIncludeList = new PhrasesIncludeList(wordsListList);
        try {
            phrasesIncludeList.saveFile();
        } catch (IOException e) {
            //TODO zucic wyjatek
            e.printStackTrace();
        }
    }

    private void saveDomainsBlocadeFile(List<DomainsList> domainsListList){
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
