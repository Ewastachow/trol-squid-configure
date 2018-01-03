package trol.domain.filemanager;

import trol.domain.filemanager.domains.SitesIncludeList;
import trol.domain.filemanager.domains.SitesList;
import trol.domain.filemanager.words.PhrasesIncludeList;
import trol.domain.filemanager.words.PhrasesList;
import trol.domain.trol_api.TrolAPI;
import trol.domain.trol_api.model.DomainsList;
import trol.domain.trol_api.model.WordsList;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

public class FC {

    public void saveConfiguration() throws IOException {
        TrolAPI trolAPI = new TrolAPI();
        List<DomainsList> domainListList = trolAPI.getDomainsListsList();
        saveDomainsFile(domainListList);



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

    private void saveDomainsFile(List<DomainsList> domainsListList) throws IOException {
        SitesIncludeList sitesIncludeList = new SitesIncludeList(domainsListList);
        sitesIncludeList.saveFile();
        for(DomainsList i: domainsListList){
            if(i.getIsActive()){
                SitesList sitesList = new SitesList(i);
                sitesList.saveFile();
            }
        }
    }
}
