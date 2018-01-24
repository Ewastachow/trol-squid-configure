package trol.blocking.database_models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "domains", schema = "estacho1")
public class DomainsEntity  implements Serializable {

    private static final long serialVersionUID = 1L;

    private int idDomain;
    private String domainString;
    private DomainsListsEntity idDomainsList;

    public DomainsEntity(){

    }

    public DomainsEntity(DomainsListsEntity idDomainsList, String domainString){
        this.domainString = domainString;
        this.idDomainsList = idDomainsList;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_domain")
    public int getIdDomain() {
        return idDomain;
    }

    public void setIdDomain(int idDomain) {
        this.idDomain = idDomain;
    }

    @Basic
    @Column(name = "domain_string")
    public String getDomainString() {
        return domainString;
    }

    public void setDomainString(String domainString) {
        this.domainString = domainString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DomainsEntity that = (DomainsEntity) o;

        if (idDomain != that.idDomain) return false;
        if (domainString != null ? !domainString.equals(that.domainString) : that.domainString != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idDomain;
        result = 31 * result + (domainString != null ? domainString.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name="id_domains_list")
    //@Column(name = "id_domains_list")
    public DomainsListsEntity getIdDomainsList() {
        return idDomainsList;
    }

    public void setIdDomainsList(DomainsListsEntity idDomainsList) {
        this.idDomainsList = idDomainsList;
    }
}
