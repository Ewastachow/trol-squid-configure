package trol.dao.headers;

import org.springframework.stereotype.Repository;
import trol.domain.database_models.TransmissionTypesEntity;
import trol.domain.trol_api.model.TransmissionType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Repository
public class TransmissionTypeDAOImpl implements TransmissionTypeDAO {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<TransmissionType> getAllTransmissionTypes() {
        String queryString = "FROM TransmissionTypesEntity";
        List<TransmissionTypesEntity> entities = entityManager.createQuery(queryString).getResultList();
        List<TransmissionType> result = entities.stream()
                .map(e -> new TransmissionType(e))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public TransmissionType getTransmissionType(int transmissionTypeId) {
        TransmissionTypesEntity entity = entityManager.find(TransmissionTypesEntity.class,transmissionTypeId);
        return new TransmissionType(entity);
    }

    @Override
    public int addTransmissionType(String transmissionTypeName) {
        TransmissionTypesEntity entity = new TransmissionTypesEntity(transmissionTypeName);
        entityManager.persist(entity);
        return entity.getIdTransmissionType();
    }

    @Override
    public void deleteTransmissionType(int transmissionTypeId) {

    }

    @Override
    public void updateTransmissionTypeProperties(TransmissionType transmissionType) {
        TransmissionTypesEntity entity = entityManager.find(
                TransmissionTypesEntity.class,
                transmissionType.getIdTransmissionType());
        //entity.setTransmissionTypeName(transmissionType.getTransmissionTypeName());
        entity.setIsActive(transmissionType.getIsActive() ? (byte) 1 : (byte) 0);
        entity.setIsTimed(transmissionType.getIsTimed() ? (byte) 1 : (byte) 0);
        entity.setTimeBegin(Time.valueOf(transmissionType.getTimeBegin()));
        entity.setTimeEnd(Time.valueOf(transmissionType.getTimeEnd()));
        entityManager.flush();
    }
}
