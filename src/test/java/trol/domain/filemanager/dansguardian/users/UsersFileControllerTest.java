package trol.domain.filemanager.dansguardian.users;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import trol.domain.filemanager.FilePaths;
import trol.domain.filemanager.FilePathsTest;
import trol.domain.trol_api.model.User;
import trol.domain.util.FileHelper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UsersFileControllerTest {

    @Before
    public void setUp() throws Exception {
        FilePathsTest.before();
    }

    @After
    public void tearDown() throws Exception {
        FilePathsTest.after();
    }

    @Test
    public void saveFileTestEmptyList() throws Exception {
        List<User> userList = new ArrayList<>();

        UsersFileController.saveFile(userList);

        List<String> createdFile = FileHelper.createLineListFromFile(FilePaths.DANSGUARDIAN_USER_PATH);
        List<String> expectedFile = new ArrayList<>();

        Assert.assertEquals("Create empty file",expectedFile,createdFile);

    }

    @Test
    public void saveFileTestNotActiveUser() throws Exception {
        List<User> userList = new ArrayList<>();
        userList.add(createUser(1,"192.168.0.22",false,false,false,0,0));

        UsersFileController.saveFile(userList);

        List<String> createdFile = FileHelper.createLineListFromFile(FilePaths.DANSGUARDIAN_USER_PATH);
        List<String> expectedFile = new ArrayList<>();

        Assert.assertEquals("Create empty file for not active user",expectedFile,createdFile);

    }

    @Test
    public void saveFileTesUsedTimeUser() throws Exception {
        List<User> userList = new ArrayList<>();
        userList.add(createUser(1,"192.168.0.22",true,false,true,2,1));

        UsersFileController.saveFile(userList);

        List<String> createdFile = FileHelper.createLineListFromFile(FilePaths.DANSGUARDIAN_USER_PATH);
        List<String> expectedFile = new ArrayList<>();

        Assert.assertEquals("Create empty file for user with time left",expectedFile,createdFile);

    }

    @Test
    public void saveFileTesActiveBlockUser() throws Exception {
        List<User> userList = new ArrayList<>();
        userList.add(createUser(1,"192.168.0.22",true,false,false,0,0));

        UsersFileController.saveFile(userList);

        List<String> createdFile = FileHelper.createLineListFromFile(FilePaths.DANSGUARDIAN_USER_PATH);
        List<String> expectedFile = new ArrayList<>();
        expectedFile.add("192.168.0.22");

        Assert.assertEquals("Create file with user becouse is active",expectedFile,createdFile);

    }

    @Test
    public void saveFileTesNoTimeLeftUser() throws Exception {
        List<User> userList = new ArrayList<>();
        userList.add(createUser(1,"192.168.0.22",true,false,true,2,2));

        UsersFileController.saveFile(userList);

        List<String> createdFile = FileHelper.createLineListFromFile(FilePaths.DANSGUARDIAN_USER_PATH);
        List<String> expectedFile = new ArrayList<>();
        expectedFile.add("192.168.0.22");

        Assert.assertEquals("Create file with user becouse used time",expectedFile,createdFile);

    }

    private User createUser(int id, String ip, boolean isActive, boolean isTimed, boolean hasDuration, Integer durationInterval, Integer usedTime){
        User result = new User();
        result.setIdUser(id);
        result.setUserIp(ip);
        result.setIsActive(isActive);
        result.setIsTimed(isTimed);
        result.setHasDuration(hasDuration);
        result.setDurationInterval(durationInterval);
        result.setUsedTime(usedTime);
        return result;
    }

}