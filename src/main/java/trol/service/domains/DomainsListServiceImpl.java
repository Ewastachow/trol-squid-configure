package trol.service.domains;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trol.exceptions.DomainsListHeaderUpdateException;
import trol.exceptions.DomainsListUpdateException;
import trol.model.domains.DomainsList;

import java.util.List;

@Service("domainsListService")
public class DomainsListServiceImpl implements DomainsListService {
    @Autowired
    private DomainsService domainsService;

    @Override
    public void updateListHeader(DomainsList newList) throws DomainsListHeaderUpdateException {
        try {
            DomainsList oldList = domainsService.getDomainsList(newList.getInfo().getId());
            List<DomainsList> domainsLists = domainsService
                    .getDomains()
                    .getDomainsLists();
            domainsLists.set(
                    domainsLists.indexOf(oldList),
                    newList
            );
        } catch (Exception e) {
            throw new DomainsListHeaderUpdateException();
        }
    }

    @Override
    public void editDomainInList(long listId, String oldDomain, String newDomain) throws DomainsListUpdateException {
        try {
            DomainsList list = domainsService.getDomainsList(listId);
            list.getDomainList()
                    .set(
                      list.getDomainList().indexOf(oldDomain),
                      newDomain
                    );
        } catch (Exception e) {
            throw new DomainsListUpdateException();
        }
    }

    @Override
    public void deleteDomainInList(long listId, String domain) throws DomainsListUpdateException {
        try {
            domainsService.getDomainsList(listId)
                    .getDomainList()
                    .remove(domain);
        } catch (Exception e) {
            throw new DomainsListUpdateException();
        }
    }

    @Override
    public void addDomainInList(long listId, String domain) throws DomainsListUpdateException {
        try {
            domainsService.getDomainsList(listId)
                    .getDomainList()
                    .add(domain);
        } catch (Exception e) {
            throw new DomainsListUpdateException();
        }
    }
}
