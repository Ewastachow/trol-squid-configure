package trol.domain.trol_api.model;

import org.hibernate.validator.constraints.Length;
import trol.domain.database_models.TransmissionTypesEntity;

import java.time.LocalTime;
import java.util.Set;
import java.util.TreeSet;

public class TransmissionType {
    private int idTransmissionType;
    @Length(min = 1, max = 100, message = "Length must between 1 and 100")
    private String transmissionTypeName;
    private boolean isActive;
    private boolean isTimed;
    private LocalTime timeBegin;
    private LocalTime timeEnd;
    private Set<Header> headersSet;

    public TransmissionType(TransmissionTypesEntity entity) {
        headersSet = new TreeSet<>();
        idTransmissionType = entity.getIdTransmissionType();
        transmissionTypeName = entity.getTransmissionTypeName();
        isActive = entity.getIsActive() == 1;
        isTimed = entity.getIsTimed() == 1;
        timeBegin = entity.getTimeBegin().toLocalTime();
        timeEnd = entity.getTimeEnd().toLocalTime();
        entity.getHeadersEntitySet().forEach(e -> headersSet.add(new Header(e)));
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void setIsTimed(boolean isTimed) {
        this.isTimed = isTimed;
    }

    public void setIdTransmissionType(int idTransmissionType) {
        this.idTransmissionType = idTransmissionType;
    }

    public void setTransmissionTypeName(String transmissionTypeName) {
        this.transmissionTypeName = transmissionTypeName;
    }

    public void setTimeBegin(LocalTime timeBegin) {
        this.timeBegin = timeBegin;
    }

    public void setTimeEnd(LocalTime timeEnd) {
        this.timeEnd = timeEnd;
    }

    public void setHeadersSet(Set<Header> headersSet) {
        this.headersSet = headersSet;
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
