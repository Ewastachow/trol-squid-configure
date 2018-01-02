package trol.service.domains;

import trol.domain.trol_api.exception.UnsuccessfulDeletException;
import trol.domain.trol_api.exception.UnsuccessfulModificationException;
import trol.domain.trol_api.model.Domain;
import trol.domain.trol_api.model.DomainsList;

import java.util.List;

public interface DomainsService {
    List<DomainsList> getDomainsLists();
    DomainsList getDomainsList(int domainsListId);
    int addDomainsList(DomainsList domainsList) throws UnsuccessfulModificationException;
    void updateDomainsListProperties(DomainsList domainsList) throws UnsuccessfulModificationException;
    void deleteDomainsList(int domainsListId) throws UnsuccessfulDeletException;

    Domain getDomain(int domainId);
    int addDomainToDomainsList(Domain domain);
    void updateDomainInList(Domain domain) throws UnsuccessfulModificationException;
    void deleteDomain(int domainId) throws UnsuccessfulDeletException;
}
