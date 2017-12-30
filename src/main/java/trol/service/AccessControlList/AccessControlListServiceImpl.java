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
        boolean isInList = accessControlList.getAccessControlList().stream()
                .anyMatch(d -> d.name.equals(domainsList.name));

        if (isInList) throw new ListNameException("List with that name already exists!");
        accessControlList.getAccessControlList().add(domainsList);
    }

    @Override
    public void deleteDomainsList(String domainsListName) throws Exception {
        Optional<DomainsList> listOptional = accessControlList.getAccessControlList().stream()
                .filter(d -> d.name.equals(domainsListName))
                .findFirst();

        if (!listOptional.isPresent()) throw new ListNameException("List with that name does not exist!");
        accessControlList.getAccessControlList().remove(listOptional.get());
    }
}
