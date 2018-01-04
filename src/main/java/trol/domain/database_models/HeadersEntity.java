package trol.domain.database_models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "headers", schema = "estacho1")
public class HeadersEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private int idHeader;
    private String headerString;
    private TransmissionTypesEntity idTransmissionType;

    public HeadersEntity() {
    }

    public HeadersEntity(String headerString, TransmissionTypesEntity idTransmissionType) {
        this.headerString = headerString;
        this.idTransmissionType = idTransmissionType;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_header")
    public int getIdHeader() {
        return idHeader;
    }

    public void setIdHeader(int idHeader) {
        this.idHeader = idHeader;
    }

    @Basic
    @Column(name = "header_string")
    public String getHeaderString() {
        return headerString;
    }

    public void setHeaderString(String headerString) {
        this.headerString = headerString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HeadersEntity that = (HeadersEntity) o;

        if (idHeader != that.idHeader) return false;
        if (headerString != null ? !headerString.equals(that.headerString) : that.headerString != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idHeader;
        result = 31 * result + (headerString != null ? headerString.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name="id_transmission_type", nullable=false)
    //@Column(name = "id_transmission_type")
    public TransmissionTypesEntity getIdTransmissionType() {
        return idTransmissionType;
    }

    public void setIdTransmissionType(TransmissionTypesEntity idTransmissionType) {
        this.idTransmissionType = idTransmissionType;
    }
}
