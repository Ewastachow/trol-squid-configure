package trol.blocking.filemanager.dansguardian.domains;

import trol.blocking.filemanager.FilePaths;
import trol.blocking.util.FileHelper;
import trol.model.DomainsList;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

class SitesList {
    private Path path;
    private DomainsList domainsList;

    SitesList(DomainsList domainsList) {
        this.domainsList = domainsList;

        path = (domainsList.getIsBlack()) ?
                Paths.get(FilePaths.DANSGUARDIAN_SITE_LISTS_PATH +domainsList.getDomainsListName().toLowerCase()+domainsList.getIdDomainsList()+"-black") :
                Paths.get(FilePaths.DANSGUARDIAN_SITE_LISTS_PATH +domainsList.getDomainsListName().toLowerCase()+domainsList.getIdDomainsList()+"-white");
    }

    void saveFile() throws IOException {
        if(!domainsList.getIsActive()) return;
        if(!domainsList.getIsBlack()){
            SiteDotBlack siteDotBlack = new SiteDotBlack(domainsList);
            siteDotBlack.saveFile();
        }
        FileHelper.saveStringListAsFile(path,generateFileListString());
    }

    private List<String> generateFileListString(){
        List<String> sitesFile = new ArrayList<>();
        if(domainsList.getIsTimed())
            sitesFile.add(FileHelper.dansguardianTimeControlLine(domainsList.getTimeBegin(), domainsList.getTimeEnd()));
        domainsList.getDomainsSet().forEach(e -> sitesFile.add(e.getDomainString()));
        return sitesFile;
    }
}
