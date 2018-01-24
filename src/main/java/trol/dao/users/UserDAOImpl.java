package trol.dao.users;

import org.springframework.stereotype.Repository;
import trol.blocking.database_models.UserEntity;
import trol.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Repository
public class UserDAOImpl implements UserDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        String queryString = "FROM UserEntity";
        List<UserEntity> entities = entityManager.createQuery(queryString).getResultList();
        List<User> result = entities.stream()
                .map(e -> new User(e))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public User getUser(int userId) {
        UserEntity entity = entityManager.find(UserEntity.class,userId);
        return new User(entity);
    }

    @Override
    public int addUser(User user) {
        UserEntity entity = new UserEntity();
        entity.setUserIp(user.getUserIp());
        entity.setIsActive(user.getIsActive() ? (byte) 1 : (byte) 0);
        entity.setIsTimed(user.getIsTimed() ? (byte) 1 : (byte) 0);
        entity.setTimeBegin(Time.valueOf(user.getTimeBegin()));
        entity.setTimeEnd(Time.valueOf(user.getTimeEnd()));
        entity.setHasDuration(user.getHasDuration() ? (byte) 1 : (byte) 0);
        entity.setDurationInterval(user.getDurationInterval());
        entity.setUsedTime(user.getUsedTime());
        entityManager.persist(entity);
        return entity.getIdUser();
    }

    @Override
    public void deleteUser(int userId) {
        UserEntity entity = entityManager.find(UserEntity.class,userId);
        entityManager.remove(entity);
    }

    @Override
    public void updateUser(User user) {
        UserEntity entity = entityManager.find(UserEntity.class,user.getIdUser());
        entity.setUserIp(user.getUserIp());
        entity.setIsActive(user.getIsActive() ? (byte) 1 : (byte) 0);
        entity.setIsTimed(user.getIsTimed() ? (byte) 1 : (byte) 0);
        entity.setTimeBegin(Time.valueOf(user.getTimeBegin()));
        entity.setTimeEnd(Time.valueOf(user.getTimeEnd()));
        entity.setHasDuration(user.getHasDuration() ? (byte) 1 : (byte) 0);
        entity.setDurationInterval(user.getDurationInterval());
        entity.setUsedTime(user.getUsedTime());
        entityManager.flush();
    }
}
