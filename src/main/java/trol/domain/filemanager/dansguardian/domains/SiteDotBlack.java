package trol.domain.filemanager.dansguardian.domains;

import trol.domain.filemanager.FilePaths;
import trol.domain.util.FileHelper;
import trol.model.DomainsList;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

class SiteDotBlack {
    private Path path;
    private DomainsList domainsList;

    SiteDotBlack(DomainsList domainsList) {
        this.domainsList = domainsList;
        path = Paths.get(FilePaths.DANSGUARDIAN_SITE_LISTS_PATH +domainsList.getDomainsListName().toLowerCase()+domainsList.getIdDomainsList()+"-black");
    }

    void saveFile() throws IOException {
        if(!domainsList.getIsActive() || domainsList.getIsBlack()) return;
        FileHelper.saveStringListAsFile(path,generateFileListString());
    }

    private List<String> generateFileListString(){
        List<String> sitesFile = new ArrayList<>();
        sitesFile.add(FileHelper.dansguardianTimeControlLine(domainsList.getTimeBegin(), domainsList.getTimeEnd()));
        sitesFile.add("**");
        return sitesFile;
    }
}
