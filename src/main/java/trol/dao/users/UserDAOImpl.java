package trol.dao.users;

import trol.domain.database_models.UserEntity;
import trol.domain.trol_api.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Time;

public class UserDAOImpl implements UserDAO {
    @PersistenceContext
    private EntityManager entityManager;

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
    public void deleteUser(User user) {

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
