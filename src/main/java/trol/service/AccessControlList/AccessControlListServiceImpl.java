package trol.service.AccessControlList;

import org.springframework.stereotype.Service;
import trol.exceptions.AccessControlLIst.ListNameException;
import trol.model.AccessControlList.AccessControlList;
import trol.model.DomainsList.DomainsList;

import java.util.List;
import java.util.Optional;

@Service("accessControlListService")
public class AccessControlListServiceImpl implements AccessControlListService {
    public static AccessControlList accessControlList = new AccessControlList();

    @Override
    public AccessControlList getAccessControlList() {
        return accessControlList;
    }

    @Override
    public void addDomainsList(DomainsList domainsList) throws Exception {
        boolean isInList = accessControlList.getDomainsLists().stream()
                .anyMatch(d -> d.name.equals(domainsList.name));

        if (isInList) throw new ListNameException("List with that name already exists!");
        accessControlList.getDomainsLists().add(domainsList);
    }

    @Override
    public DomainsList getDomainsList(String domainsListName) throws Exception {
        boolean isInList = accessControlList.getDomainsLists().stream()
                .anyMatch(d -> d.name.equals(domainsListName));
        if (!isInList) throw new ListNameException("List with that name cannot be found!");
        return accessControlList.getDomainsLists().stream()
                .filter(d -> d.name.equals(domainsListName))
                .findFirst()
                .get();
    }

    @Override
    public void deleteDomainsList(String domainsListName) throws Exception {
        Optional<DomainsList> listOptional = accessControlList.getDomainsLists().stream()
                .filter(d -> d.name.equals(domainsListName))
                .findFirst();

        if (!listOptional.isPresent()) throw new ListNameException("List with that name does not exist!");
        accessControlList.getDomainsLists().remove(listOptional.get());
    }
}
