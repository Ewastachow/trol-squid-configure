package trol.service.domains;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trol.domain.filter.domain_list.DomainList;
import trol.domain.trol_api.TrolAPI;
import trol.domain.trol_api.exception.UnsuccessfulDeletException;
import trol.domain.trol_api.exception.UnsuccessfulModificationException;
import trol.domain.trol_api.model.DomainsList;
import trol.domain.trol_api.model.Mode;

import java.sql.Time;
import java.util.List;

@Service("domainsService")
public class DomainsServiceImpl implements DomainsService {
    @Autowired
    private TrolAPI repository;


    @Override
    public List<DomainsList> getDomainsListsList() {
        return repository.getDomainsListsList();
    }

    @Override
    public DomainsList getDomainsList(int domainsListId) {
        return repository.getDomainsList(domainsListId);
    }

    @Override
    public int createNewDomainsList(String domainsListName) {
        return repository.createNewDomainsList(domainsListName);
    }

    @Override
    public int createNewDomainsList(DomainsList domainsList) {
        int id = repository.createNewDomainsList(domainsList.getDomainsListName());
        repository.changeDomainsListMode(domainsList.getIsBlack());
    }

    @Override
    public void deleteDomainsList(int domainsListId) throws UnsuccessfulDeletException {
        repository.deleteDomainsList(domainsListId);
    }

    @Override
    public void changeDomainsListMode(int domainsListId, Mode newMode) throws UnsuccessfulModificationException {
        repository.changeDomainsListMode(domainsListId,newMode);
    }

    @Override
    public void changeDomainsListTimedMode(int domainsListId,
                                           boolean isTimed) throws UnsuccessfulModificationException {
        repository.changeDomainsListTimedMode(domainsListId,isTimed);
    }

    @Override
    public void changeDomainsListTime(int domainsListId,
                                      Time newTimeBegin, Time newTimeEnd) throws UnsuccessfulModificationException {
        repository.changeDomainsListTime(domainsListId, newTimeBegin, newTimeEnd);
    }

    @Override
    public int addDomainToDomainsList(int domainsListId, String domainString) {
        return repository.addDomainToDomainsList(domainsListId, domainString);
    }

    @Override
    public void deleteDomain(int domainId) throws UnsuccessfulDeletException {
        repository.deleteDomain(domainId);
    }
}
