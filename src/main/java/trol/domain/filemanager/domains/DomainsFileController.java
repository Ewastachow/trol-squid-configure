package trol.domain.filemanager.domains;

import trol.domain.trol_api.model.DomainsList;

import java.io.IOException;
import java.util.List;

public class DomainsFileController {

    public static void saveDomainsFile(List<DomainsList> domainsListList) throws IOException {
        SitesIncludeList sitesIncludeList = new SitesIncludeList(domainsListList);
        sitesIncludeList.saveFile();
        for(DomainsList i: domainsListList){
            if(i.getIsActive()){
                SitesList sitesList = new SitesList(i);
                sitesList.saveFile();
            }
        }
    }
}