package trol.domain.trol_api.model;

import trol.domain.database_models.TransmissionTypesEntity;

import java.sql.Time;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class TransmissionType {
    private int idTransmissionType;
    private String transmissionTypeName;
    private boolean isActive;
    private boolean isTimed;
    private LocalTime timeBegin;
    private LocalTime timeEnd;
    private Set<Header> headersSet;

    public TransmissionType(TransmissionTypesEntity entity) {
        headersSet = new HashSet<>();
        idTransmissionType = entity.getIdTransmissionType();
        transmissionTypeName = entity.getTransmissionTypeName();
        isActive = entity.getIsActive() == 1;
        isTimed = entity.getIsTimed() == 1;
        timeBegin = entity.getTimeBegin().toLocalTime();
        timeEnd = entity.getTimeEnd().toLocalTime();
        entity.getHeadersEntitySet().forEach(e -> headersSet.add(new Header(e)));
    }

    public int getIdTransmissionType() {
        return idTransmissionType;
    }

    public String getTransmissionTypeName() {
        return transmissionTypeName;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public boolean getIsTimed() {
        return isTimed;
    }

    public LocalTime getTimeBegin() {
        return timeBegin;
    }

    public LocalTime getTimeEnd() {
        return timeEnd;
    }

    public Set<Header> getHeadersSet() {
        return headersSet;
    }
}
