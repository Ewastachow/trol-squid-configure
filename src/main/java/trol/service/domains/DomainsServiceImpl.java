package trol.service.domains;

import org.springframework.stereotype.Service;
import trol.exceptions.ListNameException;
import trol.model.domains.Domains;
import trol.model.domains.DomainsList;

import java.util.Optional;

@Service("accessControlListService")
public class DomainsServiceImpl implements DomainsService {
    private static Domains domains = new Domains();

    @Override
    public Domains getDomains() {
        return domains;
    }

    @Override
    public void addDomainsList(DomainsList domainsList) throws Exception {
        boolean isInList = domains.getDomainsLists().stream()
                .anyMatch(d -> d.getInfo().getId() == domainsList.getInfo().getId());
        if (isInList) {
            domainsList.getInfo().setId(domainsList.getInfo().getId() + 1);
            addDomainsList(domainsList);
            return;
            //throw new ListNameException("List with that id already exists!");
        }
        domains.getDomainsLists().add(domainsList);
    }

    @Override
    public DomainsList getDomainsList(long listId) throws Exception {
        boolean isInList = domains.getDomainsLists().stream()
                .anyMatch(d -> d.getInfo().getId() == listId);
        if (!isInList) throw new ListNameException("List with that id cannot be found!");
        return domains.getDomainsLists().stream()
                .filter(d -> d.getInfo().getId() == listId)
                .findFirst()
                .get();
    }

    @Override
    public void deleteDomainsList(long listId) throws Exception {
        Optional<DomainsList> listOptional = domains.getDomainsLists().stream()
                .filter(d -> d.getInfo().getId() == listId)
                .findFirst();
        if (!listOptional.isPresent()) throw new ListNameException("List with that id does not exist!");
        domains.getDomainsLists().remove(listOptional.get());
    }
}
