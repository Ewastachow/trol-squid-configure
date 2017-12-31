package trol.domain.database_models;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "domains_lists", schema = "estacho1")
public class DomainsListsEntity {
    private int idDomainsList;
    private String domainsListName;
    private byte isActive;
    private byte isBlack;
    private byte isTimed;
    private Time timeBegin;
    private Time timeEnd;

    @Id
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
    @Column(name = "is_active")
    public byte getIsActive() {
        return isActive;
    }

    public void setIsActive(byte isActive) {
        this.isActive = isActive;
    }

    @Basic
    @Column(name = "is_black")
    public byte getIsBlack() {
        return isBlack;
    }

    public void setIsBlack(byte isBlack) {
        this.isBlack = isBlack;
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
