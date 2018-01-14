package trol.domain.filemanager.dansguardian.domains;

import trol.domain.filemanager.FilePaths;
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

        path = (domainsList.getIsBlack()) ?
                Paths.get(FilePaths.DANSGUARDIAN_SITE_LISTS_PATH +domainsList.getDomainsListName()+domainsList.getIdDomainsList()+"-black") :
                Paths.get(FilePaths.DANSGUARDIAN_SITE_LISTS_PATH +domainsList.getDomainsListName()+domainsList.getIdDomainsList()+"-white");
    }

    public void saveFile() throws IOException {
        if(!domainsList.getIsActive()) return;
        if(!domainsList.getIsBlack()){
            SiteDotBlack siteDotBlack = new SiteDotBlack(domainsList);
            siteDotBlack.saveFile();
        }
        FileHelper.saveStringListAsFile(path,generateFileListString());
    }


    public List<String> generateFileListString(){
        List<String> sitesFile = new ArrayList<>();
        if(domainsList.getIsTimed())
            sitesFile.add(FileHelper.dansguardianTimeControlLine(domainsList.getTimeBegin(), domainsList.getTimeEnd()));
        domainsList.getDomainsSet().forEach(e -> sitesFile.add(e.getDomainString()));
        return sitesFile;
    }

//    public List<String>


}
