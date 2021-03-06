package trol.blocking.database_models;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "domains_lists", schema = "estacho1")
public class DomainsListsEntity  implements Serializable {

    private static final long serialVersionUID = 1L;

    private int idDomainsList;
    private String domainsListName;
    private byte isActive;
    private byte isBlack;
    private byte isTimed;
    private Time timeBegin;
    private Time timeEnd;

    private Set<DomainsEntity> domainsEntitySet = new HashSet<>();

    public DomainsListsEntity() {
        isBlack = 1;
        isActive = 0;
        isTimed = 0;
        timeBegin = Time.valueOf(LocalTime.of(12,0));
        timeEnd = Time.valueOf(LocalTime.of(13,0));
    }

    public DomainsListsEntity(String domainsListName){
        this();
        this.domainsListName=domainsListName;
    }

    @OneToMany(mappedBy="idDomainsList", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<DomainsEntity> getDomainsEntitySet() {
        return domainsEntitySet;
    }

    public void setDomainsEntitySet(Set<DomainsEntity> domainsEntitySet) {
        this.domainsEntitySet = domainsEntitySet;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_domains_list")
    public int getIdDomainsList() {
        return idDomainsList;
    }

    public void setIdDomainsList(int idDomainsList) {
        this.idDomainsList = idDomainsList;
    }

    @Basic
    @Column(name = "domains_list_name")
    public String getDomainsListName() {
        return domainsListName;
    }

    public void setDomainsListName(String domainsListName) {
        this.domainsListName = domainsListName;
    }

    @Basic
    @Column(name = "is_active", nullable = false, columnDefinition = "BOOLEAN FALSE")
    public byte getIsActive() {
        return isActive;
    }

    public void setIsActive(byte isActive) {
        this.isActive = isActive;
    }

    @Basic
    @Column(name = "is_black", nullable = false, columnDefinition = "BOOLEAN TRUE")
    public byte getIsBlack() {
        return isBlack;
    }

    public void setIsBlack(byte isBlack) {
        this.isBlack = isBlack;
    }

    @Basic
    @Column(name = "is_timed", nullable = false, columnDefinition = "BOOLEAN FALSE")
    public byte getIsTimed() {
        return isTimed;
    }

    public void setIsTimed(byte isTimed) {
        this.isTimed = isTimed;
    }

    @Basic
    @Column(name = "time_begin", nullable = false, columnDefinition = "TIME DEFAULT '12:00'")
    public Time getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(Time timeBegin) {
        this.timeBegin = timeBegin;
    }

    @Basic
    @Column(name = "time_end", nullable = false, columnDefinition = "TIME DEFAULT '13:00'")
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

        DomainsListsEntity that = (DomainsListsEntity) o;

        if (idDomainsList != that.idDomainsList) return false;
        if (isActive != that.isActive) return false;
        if (isBlack != that.isBlack) return false;
        if (isTimed != that.isTimed) return false;
        if (domainsListName != null ? !domainsListName.equals(that.domainsListName) : that.domainsListName != null)
            return false;
        if (timeBegin != null ? !timeBegin.equals(that.timeBegin) : that.timeBegin != null) return false;
        if (timeEnd != null ? !timeEnd.equals(that.timeEnd) : that.timeEnd != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idDomainsList;
        result = 31 * result + (domainsListName != null ? domainsListName.hashCode() : 0);
        result = 31 * result + (int) isActive;
        result = 31 * result + (int) isBlack;
        result = 31 * result + (int) isTimed;
        result = 31 * result + (timeBegin != null ? timeBegin.hashCode() : 0);
        result = 31 * result + (timeEnd != null ? timeEnd.hashCode() : 0);
        return result;
    }
}
