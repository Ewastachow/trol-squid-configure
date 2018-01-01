package trol.domain.trol_api.model;

import trol.domain.database_models.TransmissionTypesEntity;

import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

public class TransmissionType {
    private int idTransmissionType;
    private String transmissionTypeName;
    private byte isActive;
    private byte isTimed;
    private Time timeBegin;
    private Time timeEnd;
    private Set<Header> headersSet;

    public TransmissionType(TransmissionTypesEntity entity) {
        headersSet = new HashSet<>();
        idTransmissionType = entity.getIdTransmissionType();
        transmissionTypeName = entity.getTransmissionTypeName();
        isActive = entity.getIsActive();
        isTimed = entity.getIsTimed();
        timeBegin = entity.getTimeBegin();
        timeEnd = entity.getTimeEnd();
        entity.getHeadersEntitySet().forEach(e -> headersSet.add(new Header(e)));
    }

    public int getIdTransmissionType() {
        return idTransmissionType;
    }

    public String getTransmissionTypeName() {
        return transmissionTypeName;
    }

    public byte getIsActive() {
        return isActive;
    }

    public byte getIsTimed() {
        return isTimed;
    }

    public Time getTimeBegin() {
        return timeBegin;
    }

    public Time getTimeEnd() {
        return timeEnd;
    }

    public Set<Header> getHeadersSet() {
        return headersSet;
    }
}
