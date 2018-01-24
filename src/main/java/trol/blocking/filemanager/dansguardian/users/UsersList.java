package trol.blocking.filemanager.dansguardian.users;

import trol.blocking.filemanager.FilePaths;
import trol.blocking.util.FileHelper;
import trol.model.User;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

class UsersList {
    private Path path;
    private List<User> userList;

    UsersList(List<User> userList) {
        this.userList = userList;
        path = Paths.get(FilePaths.DANSGUARDIAN_USER_PATH);
    }

    void saveFile() throws IOException {
        FileHelper.saveStringListAsFile(path, generateFileListString());
    }

    private List<String> generateFileListString(){
        List<String> usersFile = new ArrayList<>();
        userList.forEach(e -> {
            if(canBeAdded(e))
                usersFile.add(e.getUserIp());
        });
        return usersFile;
    }

    private boolean canBeAdded(User u){
        boolean isActive = u.getIsActive();
        boolean hasDurationTimeUsed = !u.getHasDuration() || (u.getDurationInterval() <= u.getUsedTime());
        boolean isNotInAllowedHours = !u.getIsTimed() || LocalTime.now().isAfter(u.getTimeEnd()) || LocalTime.now().isBefore(u.getTimeBegin());
        return isActive && hasDurationTimeUsed && isNotInAllowedHours;
    }
}
