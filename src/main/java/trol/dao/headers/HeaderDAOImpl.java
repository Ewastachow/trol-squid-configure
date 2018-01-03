package trol.dao.headers;

import trol.domain.database_models.HeadersEntity;
import trol.domain.database_models.TransmissionTypesEntity;
import trol.domain.trol_api.model.Header;
import trol.domain.trol_api.model.TransmissionType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
