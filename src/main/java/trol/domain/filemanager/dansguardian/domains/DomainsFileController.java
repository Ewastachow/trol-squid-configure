package trol.domain.filemanager.dansguardian.domains;

import trol.model.DomainsList;

import java.io.IOException;
import java.util.List;

public class DomainsFileController {

    /**
     * Creates Files for blocked domains and include them into bannedsitelist or exceptionsitelist in dansguardian configuration list
     * For white list it creates also banned lists with ** to block any other sites in specific time
     * @param domainsListList
     * @throws IOException
     */
    public static void saveFile(List<DomainsList> domainsListList) throws IOException {
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
