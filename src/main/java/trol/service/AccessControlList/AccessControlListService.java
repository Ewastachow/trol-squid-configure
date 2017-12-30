package trol.service.AccessControlList;

import trol.model.AccessControlList.AccessControlList;
import trol.model.DomainsList.DomainsList;

public interface AccessControlListService {
    AccessControlList getAccessControlList();
    DomainsList getDomainsList(String domainsListName) throws Exception;
    void addDomainsList(DomainsList domainsList) throws Exception;
    void deleteDomainsList(String domainsListName) throws Exception;
}
