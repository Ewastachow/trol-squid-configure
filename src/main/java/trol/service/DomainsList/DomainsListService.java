package trol.service.DomainsList;

import trol.exceptions.DomainsListCreationException;
import trol.exceptions.DomainsListHeaderUpdateException;
import trol.exceptions.DomainsListNotFoundException;
import trol.exceptions.DomainsListUpdateException;
import trol.model.DomainsList.DomainsList;

public interface DomainsListService {
    DomainsList getList(String listName) throws DomainsListNotFoundException;
    void addList(DomainsList list) throws DomainsListCreationException;
    void editListHeader(DomainsList list) throws DomainsListHeaderUpdateException;
    void editDomainInList(String listName, String oldDomain, String newDomain) throws DomainsListUpdateException;
    void deleteDomainInList(String listName, String domain) throws DomainsListUpdateException;
    void addDomainInList(String listName, String domain) throws DomainsListUpdateException;
}
