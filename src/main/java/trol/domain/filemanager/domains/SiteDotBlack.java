package trol.domain.filemanager.domains;

import trol.domain.filemanager.FilePaths;
import trol.domain.trol_api.model.DomainsList;
import trol.domain.util.FileHelper;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SiteDotBlack {
    private Path path;
    private DomainsList domainsList;

    public SiteDotBlack(DomainsList domainsList) {
        this.domainsList = domainsList;
        path = Paths.get(FilePaths.SITE_LISTS_PATH+domainsList.getDomainsListName()+domainsList.getIdDomainsList()+"-black");
    }

    public void saveFile() throws IOException {
        if(!domainsList.getIsActive() || domainsList.getIsBlack()) return;
        FileHelper.saveStringListAsFile(path,generateFileListString());
    }

    public List<String> generateFileListString(){
        List<String> sitesFile = new ArrayList<>();
        sitesFile.add(FileHelper.dansguardianTimeControlLine(domainsList.getTimeBegin(), domainsList.getTimeEnd()));
        sitesFile.add("**");
        return sitesFile;
    }
}
