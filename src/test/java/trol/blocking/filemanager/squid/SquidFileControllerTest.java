package trol.blocking.filemanager.squid;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import trol.blocking.filemanager.FilePaths;
import trol.blocking.filemanager.FilePathsTest;
import trol.model.TransmissionType;
import trol.blocking.util.FileHelper;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SquidFileControllerTest {
    @Before
    public void setUp() throws Exception {
        FilePathsTest.before();
    }

    @After
    public void tearDown() throws Exception {
        FilePathsTest.after();
    }

    @Test
    public void saveHeaderSquidFileTestHasBeenCreated() throws Exception {
        List<TransmissionType> testEmptyList = new ArrayList<>();
        SquidFileController.saveHeaderSquidFile(testEmptyList);

        Assert.assertTrue("If file has been created",Files.exists(Paths.get(FilePaths.SQUID_CONFIGURE_PATH)));
    }

    @Test
    public void saveHeaderSquidFileTestContentForEmptyList() throws Exception {
        List<TransmissionType> testEmptyList = new ArrayList<>();
        SquidFileController.saveHeaderSquidFile(testEmptyList);
        List<String> createdFile = FileHelper.createLineListFromFile(FilePaths.SQUID_CONFIGURE_PATH);
        List<String> expectedFile = FileHelper.createLineListFromFile(FilePaths.SQUID_HEADER_CONFIGURATION_PATH);
        expectedFile.addAll(FileHelper.createLineListFromFile(FilePaths.SQUID_FOOTER_CONFIGURATION_PATH));
        Assert.assertEquals("If file include only header file and footer file",expectedFile,createdFile);
    }

//    @Test
//    public void saveHeaderSquidFileTestContentForOneElemList() throws Exception {
//        List<TransmissionType> testOneTransmissionList = new ArrayList<>();
//        testOneTransmissionList.add(new TransmissionType());
//
//        //TODO skończyć
//
//        SquidFileController.saveHeaderSquidFile(testOneTransmissionList);
//        List<String> createdFile = FileHelper.createLineListFromFile(FilePaths.SQUID_CONFIGURE_PATH);
//        List<String> expectedFile = FileHelper.createLineListFromFile(FilePaths.SQUID_HEADER_CONFIGURATION_PATH);
//        expectedFile.addAll(FileHelper.createLineListFromFile(FilePaths.SQUID_FOOTER_CONFIGURATION_PATH));
//        Assert.assertEquals("If file include only header file and footer file",createdFile,expectedFile);
//    }

}