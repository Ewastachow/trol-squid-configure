package trol.service.domains;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trol.dao.domains.DomainDAO;
import trol.dao.domains.DomainsListDAO;
import trol.domain.trol_api.exception.UnsuccessfulDeletException;
import trol.domain.trol_api.exception.UnsuccessfulModificationException;
import trol.domain.trol_api.model.Domain;
import trol.domain.trol_api.model.DomainsList;

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
    public int addDomainsList(DomainsList domainsList) throws UnsuccessfulModificationException {
        int id = domainsListDAO.addDomainsList(domainsList.getDomainsListName());
        domainsList.setIdDomainsList(id);
        domainsListDAO.updateDomainsListProperties(domainsList);
        return id;
    }

    @Override
    public void updateDomainsListProperties(DomainsList domainsList) throws UnsuccessfulModificationException {
        domainsListDAO.updateDomainsListProperties(domainsList);
    }

    @Override
    public void deleteDomainsList(int domainsListId) throws UnsuccessfulDeletException {
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
    public void updateDomainInList(Domain domain) throws UnsuccessfulModificationException {
        domainDAO.updateDomain(domain);
    }

    @Override
    public void deleteDomain(Domain domain) throws UnsuccessfulDeletException {
        domainDAO.deleteDomain(domain);
    }
}
