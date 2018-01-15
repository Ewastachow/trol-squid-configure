package trol.domain.filemanager.dansguardian.users;

import trol.domain.trol_api.model.User;

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
