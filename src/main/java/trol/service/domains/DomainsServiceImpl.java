package trol.service.domains;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trol.domain.trol_api.TrolAPI;
import trol.domain.trol_api.exception.UnsuccessfulDeletException;
import trol.domain.trol_api.exception.UnsuccessfulModificationException;
import trol.domain.trol_api.model.DomainsList;
import trol.domain.trol_api.model.Mode;
import trol.domain.util.HibernateUtil;

import javax.transaction.Transactional;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
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
    public int createNewDomainsList(DomainsList domainsList) throws UnsuccessfulModificationException {
        int id = repository.createNewDomainsList(domainsList.getDomainsListName());
        repository.changeDomainsListMode(
                id,
                domainsList.getIsBlack() ? Mode.BLACKLIST : Mode.WHITELIST
        );
        repository.changeDomainsListTimedMode(id,domainsList.getIsTimed());
        repository.changeDomainsListTime(id,domainsList.getTimeBegin(),domainsList.getTimeEnd());
        return id;
    }

    @Override
    public void updateDomainsListProperties(DomainsList domainsList) throws UnsuccessfulModificationException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        String queryString = "UPDATE DomainsListsEntity " +
                "SET domainsListName = :domainsListName, " +
                "isActive = :isActive, " +
                "isBlack = :isBlack, " +
                "isTimed = :isTimed, " +
                "timeBegin = :timeBegin, " +
                "timeEnd = :timeEnd " +
                "WHERE idDomainsList = :idDomainsList";
        Query query = session.createQuery(queryString)
                .setParameter("idDomainsList",
                        domainsList.getIdDomainsList())
                .setParameter("domainsListName",
                        domainsList.getDomainsListName())
                .setParameter("isActive",
                        domainsList.getIsActive() ? (byte) 1 : (byte) 0)
                .setParameter("isBlack",
                        domainsList.getIsBlack() ? (byte) 1 : (byte) 0)
                .setParameter("isTimed",
                        domainsList.getIsTimed() ? (byte) 1 : (byte) 0)
                .setParameter("timeBegin",
                        Time.valueOf(domainsList.getTimeBegin()))
                .setParameter("timeEnd",
                        Time.valueOf(domainsList.getTimeEnd()));
        int result = query.executeUpdate();
        if(result!=1) throw new UnsuccessfulModificationException("TrolAPI modification exception");
        session.getTransaction().commit();
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
                                      LocalTime newTimeBegin, LocalTime newTimeEnd) throws UnsuccessfulModificationException {
        repository.changeDomainsListTime(domainsListId, newTimeBegin, newTimeEnd);
    }

    @Override
    public void changeDomainInList(int idDomain, String domainString) throws UnsuccessfulModificationException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        String queryString = "UPDATE DomainsEntity SET domainString = :domainString WHERE idDomain = :idDomain";
        Query query = session.createQuery(queryString)
                .setParameter("idDomain",idDomain)
                .setParameter("domainString",domainString);
        int result = query.executeUpdate();
        if(result!=1) throw new UnsuccessfulModificationException("TrolAPI modification exception");
        session.getTransaction().commit();
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
