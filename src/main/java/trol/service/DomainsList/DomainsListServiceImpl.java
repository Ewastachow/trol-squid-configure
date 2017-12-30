package trol.service.DomainsList;

import org.springframework.stereotype.Service;
import trol.exceptions.DomainsListCreationException;
import trol.exceptions.DomainsListHeaderUpdateException;
import trol.exceptions.DomainsListNotFoundException;
import trol.exceptions.DomainsListUpdateException;
import trol.model.DomainsList.DomainsList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("domainsListService")
public class DomainsListServiceImpl implements DomainsListService {
    private static Map<String,DomainsList> testList = new HashMap<>();

    @Override
    public DomainsList getList(String listName) throws DomainsListNotFoundException {
        if (!testList.containsKey(listName)) throw new DomainsListNotFoundException("not found");
        return testList.get(listName);
    }

    @Override
    public void addList(DomainsList list) throws DomainsListCreationException {
        testList.put(list.name,list);
    }

    @Override
    public void editListHeader(DomainsList list) throws DomainsListHeaderUpdateException {
        list.setDomainList(testList.get(list.name).domainList);
        testList.replace(list.name,list);
    }

    @Override
    public void editDomainInList(String listName, String oldDomain, String newDomain) throws DomainsListUpdateException {
        List<String> domains = testList.get(listName).getDomainList();
        testList.get(listName).getDomainList().set(domains.indexOf(oldDomain),newDomain);
    }

    @Override
    public void deleteDomainInList(String listName, String domain) throws DomainsListUpdateException {
        testList.get(listName).getDomainList().remove(domain);
    }

    @Override
    public void addDomainInList(String listName, String domain) throws DomainsListUpdateException {
        testList.get(listName).getDomainList().add(domain);

    }
}
