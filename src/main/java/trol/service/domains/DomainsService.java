package trol.service.domains;

import trol.domain.trol_api.exception.UnsuccessfulDeletException;
import trol.domain.trol_api.exception.UnsuccessfulModificationException;
import trol.domain.trol_api.model.DomainsList;
import trol.domain.trol_api.model.Mode;

import java.sql.Time;
import java.util.List;

public interface DomainsService {
    List<DomainsList> getDomainsListsList();

    DomainsList getDomainsList(int domainsListId);

    int createNewDomainsList(String domainsListName);

    int createNewDomainsList(DomainsList domainList);

    void deleteDomainsList(int domainsListId) throws UnsuccessfulDeletException ;

    void changeDomainsListMode(int domainsListId, Mode newMode) throws UnsuccessfulModificationException;

    void changeDomainsListTimedMode(int domainsListId, boolean isTimed) throws UnsuccessfulModificationException ;

    void changeDomainsListTime(int domainsListId,
                               Time newTimeBegin, Time newTimeEnd) throws UnsuccessfulModificationException ;

    int addDomainToDomainsList(int domainsListId, String domainString);

    void deleteDomain(int domainId) throws UnsuccessfulDeletException;
}
