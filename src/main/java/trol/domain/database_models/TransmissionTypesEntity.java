package trol.domain.database_models;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "transmission_types", schema = "estacho1")
public class TransmissionTypesEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private int idTransmissionType;
    private String transmissionTypeName;
    private byte isActive;
    private byte isTimed;
    private Time timeBegin;
    private Time timeEnd;

    private Set<HeadersEntity> headersEntitySet = new HashSet<>();

    public Set<HeadersEntity> getHeadersEntitySet() {
        return headersEntitySet;
    }

    public void setHeadersEntitySet(Set<HeadersEntity> headersEntitySet) {
        this.headersEntitySet = headersEntitySet;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transmission_type")
    public int getIdTransmissionType() {
        return idTransmissionType;
    }

    public void setIdTransmissionType(int idTransmissionType) {
        this.idTransmissionType = idTransmissionType;
    }

    @Basic
    @Column(name = "transmission_type_name")
    public String getTransmissionTypeName() {
        return transmissionTypeName;
    }

    public void setTransmissionTypeName(String transmissionTypeName) {
        this.transmissionTypeName = transmissionTypeName;
    }

    @Basic
    @Column(name = "is_active")
    public byte getIsActive() {
        return isActive;
    }

    public void setIsActive(byte isActive) {
        this.isActive = isActive;
    }

    @Basic
    @Column(name = "is_timed")
    public byte getIsTimed() {
        return isTimed;
    }

    public void setIsTimed(byte isTimed) {
        this.isTimed = isTimed;
    }

    @Basic
    @Column(name = "time_begin")
    public Time getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(Time timeBegin) {
        this.timeBegin = timeBegin;
    }

    @Basic
    @Column(name = "time_end")
    public Time getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Time timeEnd) {
        this.timeEnd = timeEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransmissionTypesEntity that = (TransmissionTypesEntity) o;

        if (idTransmissionType != that.idTransmissionType) return false;
        if (isActive != that.isActive) return false;
        if (isTimed != that.isTimed) return false;
        if (transmissionTypeName != null ? !transmissionTypeName.equals(that.transmissionTypeName) : that.transmissionTypeName != null)
            return false;
        if (timeBegin != null ? !timeBegin.equals(that.timeBegin) : that.timeBegin != null) return false;
        if (timeEnd != null ? !timeEnd.equals(that.timeEnd) : that.timeEnd != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idTransmissionType;
        result = 31 * result + (transmissionTypeName != null ? transmissionTypeName.hashCode() : 0);
        result = 31 * result + (int) isActive;
        result = 31 * result + (int) isTimed;
        result = 31 * result + (timeBegin != null ? timeBegin.hashCode() : 0);
        result = 31 * result + (timeEnd != null ? timeEnd.hashCode() : 0);
        return result;
    }
}
