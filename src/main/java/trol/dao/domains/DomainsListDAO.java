package trol.dao.domains;

import trol.model.DomainsList;

import java.util.List;

public interface DomainsListDAO {
    List<DomainsList> getAllDomainsLists();
    DomainsList getDomainsList(int domainsListId);
    int addDomainsList(String domainsListName);
    void deleteDomainsList(int domainsListId);
    void updateDomainsListProperties(DomainsList domainsList);
}
