package trol.domain.filemanager.dansguardian.words;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import trol.domain.filemanager.FilePaths;
import trol.domain.filemanager.FilePathsTest;
import trol.domain.trol_api.model.Word;
import trol.domain.trol_api.model.WordsList;
import trol.domain.util.FileHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.*;

public class WordsFileControllerTest {

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
        Set<Word> wordsSet = new TreeSet<>();
        wordsSet.add(createWord(1,"Markpol",1));

        List<WordsList> wordsListList = new ArrayList<>();
        wordsListList.add(createWordsList(1,"MarkpolList",false,false,wordsSet));

        WordsFileController.saveFile(wordsListList);

        List<String> createdFile = FileHelper.createLineListFromFile(FilePaths.DANSGUARDIAN_PHRASE_INCLUDE_LIST_PATH);
        List<String> expectedFile = new ArrayList<>();

        Assert.assertEquals("Create empty file",expectedFile,createdFile);
    }

    @Test
    public void saveFileTestOneInclude() throws Exception {
        Set<Word> wordsSet = new TreeSet<>();
        wordsSet.add(createWord(1,"Markpol",1));

        List<WordsList> wordsListList = new ArrayList<>();
        wordsListList.add(createWordsList(1,"MarkpolList",true,false, wordsSet));

        WordsFileController.saveFile(wordsListList);

        List<String> createdFile = FileHelper.createLineListFromFile(FilePaths.DANSGUARDIAN_PHRASE_INCLUDE_LIST_PATH);
        List<String> expectedFile = new ArrayList<>();
        expectedFile.add(".Include<" + FilePaths.DANSGUARDIAN_PHRASE_LISTS_PATH + "markpollist1>");

        Assert.assertEquals("Create one include file",expectedFile,createdFile);
    }

    @Test
    public void saveFileTestOneWordList() throws Exception {
        Set<Word> wordsSet = new TreeSet<>();
        wordsSet.add(createWord(1,"Markpol",1));

        List<WordsList> wordsListList = new ArrayList<>();
        wordsListList.add(createWordsList(1,"MarkpolList",true,false, wordsSet));

        WordsFileController.saveFile(wordsListList);

        List<String> createdFile = FileHelper.createLineListFromFile(FilePaths.DANSGUARDIAN_PHRASE_LISTS_PATH+"markpollist1");
        List<String> expectedFile = new ArrayList<>();
        expectedFile.add("<Markpol>");

        Assert.assertEquals("Create one word file",expectedFile,createdFile);
    }

    private WordsList createWordsList(int id, String name, boolean isActive, boolean isTimed, Set<Word> set){
        WordsList result = new WordsList();
        result.setIdWordsList(id);
        result.setWordsListName(name);
        result.setIsActive(isActive);
        result.setIsTimed(isTimed);
        result.setWordsSet(set);
        return result;
    }

    private Word createWord(int id, String name, int listId){
        Word result = new Word();
        result.setIdWord(id);
        result.setWordString(name);
        result.setIdWordsList(listId);
        return result;
    }

}