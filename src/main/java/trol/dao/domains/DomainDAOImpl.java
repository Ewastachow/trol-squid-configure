package trol.dao.domains;

import org.springframework.stereotype.Repository;
import trol.blocking.database_models.DomainsEntity;
import trol.blocking.database_models.DomainsListsEntity;
import trol.model.Domain;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
@Repository
public class DomainDAOImpl implements DomainDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Domain getDomain(int domainId) {
        DomainsEntity entity = entityManager.find(DomainsEntity.class,domainId);
        return new Domain(entity);
    }

    @Override
    public int addDomain(Domain domain) {
        DomainsListsEntity listsEntity = entityManager.getReference(
                DomainsListsEntity.class,
                domain.getIdDomainsList()
        );
        DomainsEntity entity = new DomainsEntity(listsEntity,domain.getDomainString());
        entityManager.persist(entity);
        return entity.getIdDomain();
    }

    @Override
    public void deleteDomain(Domain domain) {
        DomainsListsEntity listsEntity = entityManager.find(DomainsListsEntity.class,domain.getIdDomainsList());
        DomainsEntity domainsEntity = entityManager.find(DomainsEntity.class,domain.getIdDomain());
        listsEntity.getDomainsEntitySet().remove(domainsEntity);
        entityManager.remove(domainsEntity);
    }

    @Override
    public void updateDomain(Domain domain) {
        DomainsEntity entity = entityManager.find(DomainsEntity.class,domain.getIdDomain());
        entity.setDomainString(domain.getDomainString());
        entityManager.flush();
    }
}
