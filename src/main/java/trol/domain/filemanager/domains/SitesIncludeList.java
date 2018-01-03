package trol.domain.filemanager.domains;

import trol.domain.filemanager.FilePaths;
import trol.domain.trol_api.model.DomainsList;
import trol.domain.util.FileHelper;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SitesIncludeList {
    Path pathWhite;
    Path pathBlack;
    List<DomainsList> domainsListList;

    public SitesIncludeList(List<DomainsList> domainsListList) {
        this.domainsListList = domainsListList;
        pathWhite = Paths.get(FilePaths.SITE_WHITE_INCLUDE_LIST_PATH);
        pathBlack = Paths.get(FilePaths.SITE_BLACK_INCLUDE_LIST_PATH);

    }

    public void saveFile() throws IOException {
        FileHelper.saveStringListAsFile(pathBlack, generateBlackFileListstring());
        FileHelper.saveStringListAsFile(pathWhite, generateWhiteFileListstring());
    }

    public List<String> generateBlackFileListstring(){
        List<String> sitesFile = new ArrayList<>();
//        domainsListList.forEach(e -> {
//            if (e.getIsActive() && e.getIsBlack())
//                sitesFile.add(".Include<" + FilePaths.PHRASE_LISTS_PATH + e.getDomainsListName() + ">");
//        });
        boolean isAnyWhite = false;
        for(DomainsList i: domainsListList){
            if (i.getIsActive() && i.getIsBlack())
                sitesFile.add(wordLine(i.getDomainsListName()));

        }
        if (isAnyWhite) sitesFile.add("**");
        return sitesFile;
    }

    public List<String> generateWhiteFileListstring(){
        List<String> sitesFile = new ArrayList<>();
        domainsListList.forEach(e -> {
            if (e.getIsActive() && !e.getIsBlack())
                sitesFile.add(wordLine(e.getDomainsListName()));
        });
        return sitesFile;
    }

    private String wordLine(String word){
        return ".Include<"+FilePaths.SITE_LISTS_PATH+word+">";
    }

    //iteruje, jezeli wystepuje jakakolwiek white lista, to wpisuje **
}
