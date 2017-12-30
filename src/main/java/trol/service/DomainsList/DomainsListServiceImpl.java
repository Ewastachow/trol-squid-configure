package trol.service.DomainsList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trol.exceptions.DomainsListHeaderUpdateException;
import trol.exceptions.DomainsListUpdateException;
import trol.model.DomainsList.DomainsList;
import trol.service.AccessControlList.AccessControlListService;
import java.util.List;

@Service("domainsListService")
public class DomainsListServiceImpl implements DomainsListService {
    @Autowired
    private AccessControlListService accessControlListService;

//    @Override
////    public DomainsList getList(String listName) throws DomainsListNotFoundException {
////        if (list.stream().noneMatch(d -> d.name == listName)) throw  new DomainsListNotFoundException();
////        return list.stream().filter(d -> d.name.equals(listName)).findFirst().get();
////    }
////
////    @Override
////    public void addList(DomainsList list) throws DomainsListCreationException {
////        testList.put(list.name,list);
////    }

    @Override
    public void editListHeader(DomainsList newList) throws DomainsListHeaderUpdateException {
        try {
            DomainsList oldList = accessControlListService.getDomainsList(newList.name);
            List<DomainsList> domainsLists = accessControlListService
                    .getAccessControlList()
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
    public void editDomainInList(String listName, String oldDomain, String newDomain) throws DomainsListUpdateException {
        try {
            DomainsList list = accessControlListService.getDomainsList(listName);
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
    public void deleteDomainInList(String listName, String domain) throws DomainsListUpdateException {
        try {
            accessControlListService.getDomainsList(listName)
                    .getDomainList()
                    .remove(domain);
        } catch (Exception e) {
            throw new DomainsListUpdateException();
        }
    }

    @Override
    public void addDomainInList(String listName, String domain) throws DomainsListUpdateException {
        try {
            accessControlListService.getDomainsList(listName)
                    .getDomainList()
                    .add(domain);
        } catch (Exception e) {
            throw new DomainsListUpdateException();
        }
    }
}
