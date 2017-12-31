package trol.service.domains;

import trol.model.domains.Domains;
import trol.model.domains.DomainsList;

public interface DomainsService {
    Domains getDomains();
    DomainsList getDomainsList(long listId) throws Exception;
    void addDomainsList(DomainsList domainsList) throws Exception;
    void deleteDomainsList(long listId) throws Exception;
}
