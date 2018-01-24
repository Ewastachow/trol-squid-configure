package trol.service.domains;

import trol.model.Domain;
import trol.model.DomainsList;

import java.util.List;

public interface DomainsService {
    List<DomainsList> getDomainsLists();
    DomainsList getDomainsList(int domainsListId);
    int addDomainsList(DomainsList domainsList);
    void updateDomainsListProperties(DomainsList domainsList);
    void deleteDomainsList(int domainsListId);

    Domain getDomain(int domainId);
    int addDomainToDomainsList(Domain domain);
    void updateDomainInList(Domain domain);
    void deleteDomain(Domain domain);
}
