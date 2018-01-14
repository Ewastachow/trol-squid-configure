package trol.domain.filemanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import trol.dao.domains.DomainsListDAO;
import trol.dao.headers.TransmissionTypeDAO;
import trol.dao.users.UserDAO;
import trol.dao.words.WordsListDAO;
import trol.domain.filemanager.dansguardian.domains.DomainsFileController;
import trol.domain.filemanager.dansguardian.users.UsersFileController;
import trol.domain.filemanager.squid.SquidFileController;
import trol.domain.filemanager.dansguardian.words.WordsFileController;
import trol.domain.terminal.TerminalExecute;

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

            DomainsFileController.saveFile(domainsListDAO.getAllDomainsLists());
            WordsFileController.saveFile(wordsListDAO.getAllWordsLists());
            UsersFileController.saveFile(userDAO.getAllUsers());
            SquidFileController.saveHeaderSquidFile(transmissionTypeDAO.getAllTransmissionTypes());

            TerminalExecute terminalExecute = new TerminalExecute();
            String outputSquid = terminalExecute.executeCommand("systemctl restart squid.service");
            System.out.printf(outputSquid);
            String outputDans = terminalExecute.executeCommand("systemctl restart dansguardian.service");
            System.out.printf(outputSquid);
        } catch (IOException e) {
            System.out.printf("Blad zapisu");
        } catch (InterruptedException e) {
            System.out.printf("Blad restartu");
        } finally {
            System.out.println("koncze prace " + this);
            state = SaveState.FREE;
        }
    }
}
