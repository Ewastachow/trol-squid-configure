package trol.dao.appusers;

import org.springframework.stereotype.Repository;
import trol.domain.database_models.AppUserEntity;
import trol.model.AppUser;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Transactional
@Repository
public class AppUsersDAOImpl implements AppUsersDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public AppUser getAppUserByName(String name) {
        String queryString = "FROM AppUserEntity WHERE username = :name";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("name", name);
        AppUserEntity entity = (AppUserEntity) query.getSingleResult();
        return new AppUser(entity);
    }

    @Override
    public void updateAppUserPassword(String username, String password) {
        String queryString = "FROM AppUserEntity WHERE username = :name";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("name", username);
        AppUserEntity entity = (AppUserEntity) query.getSingleResult();
        entity.setPassword(password);
        entityManager.flush();
    }


}
