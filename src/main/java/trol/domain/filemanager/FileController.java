package trol.domain.filemanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import trol.dao.domains.DomainsListDAO;
import trol.dao.headers.TransmissionTypeDAO;
import trol.dao.users.UserDAO;
import trol.dao.words.WordsListDAO;
import trol.domain.filemanager.domains.DomainsFileController;
import trol.domain.filemanager.domains.SitesIncludeList;
import trol.domain.filemanager.domains.SitesList;
import trol.domain.filemanager.trash.DomainList;
import trol.domain.filemanager.words.PhrasesIncludeList;
import trol.domain.filemanager.words.PhrasesList;
import trol.domain.filemanager.words.WordsFileController;
import trol.domain.trol_api.model.DomainsList;
import trol.domain.trol_api.model.TransmissionType;
import trol.domain.trol_api.model.WordsList;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

@Component
@Scope("singleton")
public class FileController {

    @Autowired
    private DomainsListDAO domainsListDAO;
    @Autowired
    private WordsListDAO wordsListDAO;
    @Autowired
    private TransmissionTypeDAO transmissionTypeDAO;
    @Autowired
    private UserDAO userDAO;

    private volatile SaveState state = SaveState.FREE;

    public SaveState getState() {
        return state;
    }

    @Async
    public void saveConfiguration() {
        if (state.equals(SaveState.BUSY)) {
            System.out.println("nie przerywac, pracuje");
            return;
        }
        System.out.println("zaczynam prace " + this);
        state = SaveState.BUSY;

        try {
            DomainsFileController.saveDomainsFile(domainsListDAO.getAllDomainsLists());
            WordsFileController.saveDomainsFile(wordsListDAO.getAllWordsLists());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("koncze prace " + this);
        state = SaveState.FREE;
    }
}
