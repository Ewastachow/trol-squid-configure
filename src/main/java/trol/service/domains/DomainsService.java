package trol.service.domains;

import trol.domain.trol_api.exception.UnsuccessfulDeletException;
import trol.domain.trol_api.exception.UnsuccessfulModificationException;
import trol.domain.trol_api.model.DomainsList;
import trol.domain.trol_api.model.Mode;


import java.time.LocalTime;
import java.util.List;

public interface DomainsService {
    List<DomainsList> getDomainsListsList();

    DomainsList getDomainsList(int domainsListId);

    int createNewDomainsList(String domainsListName);

    int createNewDomainsList(DomainsList domainsList) throws UnsuccessfulModificationException;

    void updateDomainsListProperties(DomainsList domainsList) throws UnsuccessfulModificationException;

    void deleteDomainsList(int domainsListId) throws UnsuccessfulDeletException;

    void changeDomainsListMode(int domainsListId, Mode newMode) throws UnsuccessfulModificationException;

    void changeDomainsListTimedMode(int domainsListId, boolean isTimed) throws UnsuccessfulModificationException ;

    void changeDomainsListTime(int domainsListId,
                               LocalTime newTimeBegin, LocalTime newTimeEnd) throws UnsuccessfulModificationException ;

    void changeDomainInList(int idDomain, String domainString) throws UnsuccessfulModificationException;

    int addDomainToDomainsList(int domainsListId, String domainString);

    void deleteDomain(int domainId) throws UnsuccessfulDeletException;
}
