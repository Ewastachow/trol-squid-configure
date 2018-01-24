package trol.blocking.filemanager.dansguardian.users;

import trol.model.User;

import java.io.IOException;
import java.util.List;

public class UsersFileController {
    /**
     * Saves currently bloked ip-s to bannediplist in dansguardian configuration list
     * @param userList
     * @throws IOException
     */
    public static void saveFile(List<User> userList) throws IOException {
        UsersList usersList = new UsersList(userList);
        usersList.saveFile();
    }
}
