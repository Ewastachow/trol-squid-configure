package trol.domain.filemanager.domains;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import trol.dao.domains.DomainDAO;
import trol.dao.domains.DomainDAOImpl;
import trol.dao.domains.DomainsListDAO;
import trol.dao.domains.DomainsListDAOImpl;
import trol.domain.filemanager.trash.DomainList;
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
