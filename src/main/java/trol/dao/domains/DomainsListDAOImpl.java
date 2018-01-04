package trol.dao.domains;

import org.springframework.stereotype.Repository;
import trol.domain.database_models.DomainsListsEntity;
import trol.domain.trol_api.model.DomainsList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Repository
public class DomainsListDAOImpl implements DomainsListDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<DomainsList> getAllDomainsLists() {
        String queryString = "FROM DomainsListsEntity";
        List<DomainsListsEntity> entities = entityManager.createQuery(queryString).getResultList();
        List<DomainsList> result = entities.stream()
                .map(e -> new DomainsList(e))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public DomainsList getDomainsList(int domainsListId) {
        DomainsListsEntity entity = entityManager.find(DomainsListsEntity.class,domainsListId);
        return new DomainsList(entity);
    }

    @Override
    public int addDomainsList(String domainsListName) {
        DomainsListsEntity entity = new DomainsListsEntity(domainsListName);
        entityManager.persist(entity);
        return entity.getIdDomainsList();
    }

    @Override
    public void deleteDomainsList(int domainsListId) {
        //String queryString = "DELETE DomainsListsEntity WHERE idDomainsList = :domainsListId";
        //Query query = entityManager.createQuery(queryString);
        //query.setParameter("domainsListId",domainsListId);
        //query.executeUpdate();

        DomainsListsEntity entity = entityManager.find(DomainsListsEntity.class,domainsListId);
        entity.getDomainsEntitySet().clear();
        entityManager.remove(entity);
    }

    @Override
    public void updateDomainsListProperties(DomainsList domainsList) {
        DomainsListsEntity entity = entityManager.find(
                DomainsListsEntity.class,
                domainsList.getIdDomainsList());
        entity.setDomainsListName(domainsList.getDomainsListName());
        entity.setIsActive(domainsList.getIsActive() ? (byte) 1 : (byte) 0);
        entity.setIsBlack(domainsList.getIsBlack() ? (byte) 1 : (byte) 0);
        entity.setIsTimed(domainsList.getIsTimed() ? (byte) 1 : (byte) 0);
        entity.setTimeBegin(Time.valueOf(domainsList.getTimeBegin()));
        entity.setTimeEnd(Time.valueOf(domainsList.getTimeEnd()));
        entityManager.flush();
    }
}
