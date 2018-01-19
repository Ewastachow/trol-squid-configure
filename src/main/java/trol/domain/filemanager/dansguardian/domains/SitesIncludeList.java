package trol.domain.filemanager.dansguardian.domains;

import trol.domain.filemanager.FilePaths;
import trol.domain.util.FileHelper;
import trol.model.DomainsList;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

class SitesIncludeList {
    private Path pathWhite;
    private Path pathBlack;
    private List<DomainsList> domainsListList;

    SitesIncludeList(List<DomainsList> domainsListList) {
        this.domainsListList = domainsListList;
        pathWhite = Paths.get(FilePaths.DANSGUARDIAN_SITE_WHITE_INCLUDE_LIST_PATH);
        pathBlack = Paths.get(FilePaths.DANSGUARDIAN_SITE_BLACK_INCLUDE_LIST_PATH);
    }

    void saveFile() throws IOException {
        List<String> blackListString = generateBlackFileListstring();
        blackListString.addAll(generateBlackForWhiteListString());
        FileHelper.saveStringListAsFile(pathBlack, blackListString);
        FileHelper.saveStringListAsFile(pathWhite, generateWhiteFileListstring());
    }

    private List<String> generateBlackFileListstring(){
        List<String> sitesFile = new ArrayList<>();
        for(DomainsList i: domainsListList){
            if (i.getIsActive() && i.getIsBlack())
                sitesFile.add(".Include<"+FilePaths.DANSGUARDIAN_SITE_LISTS_PATH +i.getDomainsListName().toLowerCase()+i.getIdDomainsList()+"-black>");

        }
        return sitesFile;
    }

    private List<String> generateWhiteFileListstring(){
        List<String> sitesFile = new ArrayList<>();
        domainsListList.forEach(e -> {
            if (e.getIsActive() && !e.getIsBlack()){
                sitesFile.add(".Include<"+FilePaths.DANSGUARDIAN_SITE_LISTS_PATH +e.getDomainsListName().toLowerCase()+e.getIdDomainsList()+"-white>");
            }
        });
        return sitesFile;
    }

    private List<String> generateBlackForWhiteListString(){
        List<String> sitesFile = new ArrayList<>();
        domainsListList.forEach(e -> {
            if (e.getIsActive() && !e.getIsBlack()){
                sitesFile.add(".Include<"+FilePaths.DANSGUARDIAN_SITE_LISTS_PATH +e.getDomainsListName().toLowerCase()+e.getIdDomainsList()+"-black>");
            }
        });
        return sitesFile;
    }

}
