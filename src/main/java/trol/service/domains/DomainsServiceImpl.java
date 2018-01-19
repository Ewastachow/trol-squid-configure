package trol.service.domains;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trol.dao.domains.DomainDAO;
import trol.dao.domains.DomainsListDAO;
import trol.model.Domain;
import trol.model.DomainsList;

import java.util.List;

@Service("domainsService")
public class DomainsServiceImpl implements DomainsService {
    @Autowired
    private DomainDAO domainDAO;

    @Autowired
    private DomainsListDAO domainsListDAO;

    @Override
    public List<DomainsList> getDomainsLists() {
        return domainsListDAO.getAllDomainsLists();
    }

    @Override
    public DomainsList getDomainsList(int domainsListId) {
        return domainsListDAO.getDomainsList(domainsListId);
    }

    @Override
    public int addDomainsList(DomainsList domainsList){
        int id = domainsListDAO.addDomainsList(domainsList.getDomainsListName());
        domainsList.setIdDomainsList(id);
        domainsListDAO.updateDomainsListProperties(domainsList);
        return id;
    }

    @Override
    public void updateDomainsListProperties(DomainsList domainsList){
        domainsListDAO.updateDomainsListProperties(domainsList);
    }

    @Override
    public void deleteDomainsList(int domainsListId){
        domainsListDAO.deleteDomainsList(domainsListId);
    }

    @Override
    public Domain getDomain(int domainId) {
        return domainDAO.getDomain(domainId);
    }

    @Override
    public int addDomainToDomainsList(Domain domain) {
        return domainDAO.addDomain(domain);
    }

    @Override
    public void updateDomainInList(Domain domain){
        domainDAO.updateDomain(domain);
    }

    @Override
    public void deleteDomain(Domain domain){
        domainDAO.deleteDomain(domain);
    }
}
