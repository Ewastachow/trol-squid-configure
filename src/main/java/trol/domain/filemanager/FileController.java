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
import trol.domain.filemanager.squid.SquidFileController;
import trol.domain.filemanager.words.WordsFileController;

import java.io.IOException;

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
            WordsFileController.saveWordsFile(wordsListDAO.getAllWordsLists());
            SquidFileController.saveUsersAndHeadersFile(userDAO.getAllUsers(),transmissionTypeDAO.getAllTransmissionTypes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("koncze prace " + this);
        state = SaveState.FREE;
    }
}
