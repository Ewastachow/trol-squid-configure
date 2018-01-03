package trol.domain.filemanager;

import trol.domain.trol_api.model.DomainsList;
import trol.domain.util.FileHelper;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SitesList {
    private Path path;
    private DomainsList domainsList;

    public SitesList(DomainsList domainsList) {
        this.domainsList = domainsList;
        path = Paths.get(FilePaths.SITE_LISTS_PATH+domainsList.getDomainsListName());
    }

    public void saveFile() throws IOException {
        if(!domainsList.getIsActive()) return;
        FileHelper.saveStringListAsFile(path,generateFileListstring());
    }

    public List<String> generateFileListstring(){
        List<String> sitesFile = new ArrayList<>();
        sitesFile.add(FileHelper.dansguardianTimeControlLine(domainsList.getTimeBegin(), domainsList.getTimeEnd()));
        domainsList.getDomainsSet().forEach(e -> sitesFile.add(e.getDomainString()));
        return sitesFile;
    }


}
