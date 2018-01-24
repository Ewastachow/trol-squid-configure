package trol.dao.headers;

import org.springframework.stereotype.Repository;
import trol.blocking.database_models.HeadersEntity;
import trol.blocking.database_models.TransmissionTypesEntity;
import trol.model.Header;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
@Repository
public class HeaderDAOImpl implements HeaderDAO {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Header getHeader(int headerId) {
        HeadersEntity entity = entityManager.find(HeadersEntity.class,headerId);
        return new Header(entity);
    }

    @Override
    public int addHeader(Header header) {
        TransmissionTypesEntity typeEntity = entityManager.getReference(
                TransmissionTypesEntity.class,
                header.getIdTransmissionType()
        );
        HeadersEntity entity = new HeadersEntity(header.getHeaderString(),typeEntity);
        entityManager.persist(entity);
        return entity.getIdHeader();
    }

    @Override
    public void deleteHeader(Header header) {

    }

    @Override
    public void updateHeader(Header header) {
        HeadersEntity entity = entityManager.find(HeadersEntity.class,header.getIdHeader());
        entity.setHeaderString(header.getHeaderString());
        entityManager.flush();
    }
}
