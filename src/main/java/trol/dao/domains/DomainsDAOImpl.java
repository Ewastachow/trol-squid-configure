package trol.dao.domains;

import org.springframework.stereotype.Repository;
import trol.domain.database_models.DomainsEntity;
import trol.domain.database_models.DomainsListsEntity;
import trol.domain.trol_api.model.Domain;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
@Repository
public class DomainsDAOImpl implements DomainsDAO {
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
    public void deleteDomain(int domainId) {
        entityManager.remove(
                entityManager.find(DomainsEntity.class,domainId)
        );
    }

    @Override
    public void updateDomain(Domain domain) {
        DomainsEntity entity = entityManager.find(DomainsEntity.class,domain.getIdDomain());
        entity.setDomainString(domain.getDomainString());
        entityManager.flush();
    }
}
