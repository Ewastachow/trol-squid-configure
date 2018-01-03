package trol.domain.filemanager;

import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import trol.domain.filemanager.trash.DomainList;
import trol.domain.filemanager.words.PhrasesIncludeList;
import trol.domain.filemanager.words.PhrasesList;
import trol.domain.trol_api.model.DomainsList;
import trol.domain.trol_api.model.WordsList;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

@Component
@Scope("singleton")
public class FileController {

    private volatile SaveState state = SaveState.FREE;

    public SaveState getState() {
        return state;
    }

    @Async
    public void saveConfiguration(){
        if (state.equals(SaveState.BUSY)){
            System.out.println("nie przerywac, pracuje");
            return;
        }
        System.out.println("zaczynam prace "+ this);
        state = SaveState.BUSY;
        LocalTime now = LocalTime.now().plusSeconds(10);
        while (LocalTime.now().isBefore(now)){
            //System.out.println(LocalTime.now());
        }
        System.out.println("koncze prace "+ this);
        state = SaveState.FREE;
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