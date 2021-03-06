package trol.blocking.filemanager.dansguardian.domains;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import trol.blocking.filemanager.FilePaths;
import trol.blocking.filemanager.FilePathsTest;
import trol.model.Domain;
import trol.model.DomainsList;
import trol.blocking.util.FileHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class DomainsFileControllerTest {

    @Before
    public void setUp() throws Exception {
        FilePathsTest.before();
    }

    @After
    public void tearDown() throws Exception {
        FilePathsTest.after();
    }

    @Test
    public void saveFileTestEmpty() throws Exception {
        List<DomainsList> domainsListList = new ArrayList<>();
        DomainsFileController.saveFile(domainsListList);
        List<String> createdFile = FileHelper.createLineListFromFile(FilePaths.DANSGUARDIAN_SITE_BLACK_INCLUDE_LIST_PATH);
        List<String> expectedFile = new ArrayList<>();
        Assert.assertEquals("Create empty site include file",expectedFile,createdFile);
    }

    @Test
    public void saveFileTestBlackNotActive() throws Exception {
        Set<Domain> domainSet = new TreeSet<>();
        List<DomainsList> domainsListList = new ArrayList<>();
        domainsListList.add(createDomainsList(1,"SiteTest",false,true,domainSet));

        DomainsFileController.saveFile(domainsListList);

        List<String> createdFile = FileHelper.createLineListFromFile(FilePaths.DANSGUARDIAN_SITE_BLACK_INCLUDE_LIST_PATH);
        List<String> expectedFile = new ArrayList<>();

        Assert.assertEquals("Create empty black site include file becouse not active",expectedFile,createdFile);
    }


    private DomainsList createDomainsList(int id, String name, boolean isActive, boolean isBlack, Set<Domain> set){
        DomainsList result = new DomainsList();
        result.setIdDomainsList(id);
        result.setDomainsListName(name);
        result.setIsActive(isActive);
        result.setIsBlack(isBlack);
        result.setDomainsSet(set);
        return result;
    }

    private Domain createDomain(int id, String name, int listId){
        Domain result = new Domain();
        result.setIdDomain(id);
        result.setDomainString(name);
        result.setIdDomainsList(listId);
        return result;
    }



}