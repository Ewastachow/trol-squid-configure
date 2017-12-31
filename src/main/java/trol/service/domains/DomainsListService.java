package trol.service.domains;

import trol.exceptions.DomainsListHeaderUpdateException;
import trol.exceptions.DomainsListUpdateException;
import trol.model.domains.DomainsList;

public interface DomainsListService {
    void updateListHeader(DomainsList list) throws DomainsListHeaderUpdateException;
    void editDomainInList(long listId, String oldDomain, String newDomain) throws DomainsListUpdateException;
    void deleteDomainInList(long listId, String domain) throws DomainsListUpdateException;
    void addDomainInList(long listId, String domain) throws DomainsListUpdateException;
}
