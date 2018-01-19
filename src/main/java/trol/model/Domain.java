package trol.model;

import trol.domain.database_models.DomainsEntity;
import trol.validation.TrolDomain;

public class Domain implements Comparable<Domain>{
    private int idDomain;
    @TrolDomain
    private String domainString;
    private int idDomainsList;

    public Domain(){

    }

    public Domain(DomainsEntity entity) {
        idDomain = entity.getIdDomain();
        domainString = entity.getDomainString();
        idDomainsList = entity.getIdDomainsList().getIdDomainsList();
    }

    public void setDomainString(String domainString) {
        this.domainString = domainString;
    }

    public void setIdDomainsList(int idDomainsList) {
        this.idDomainsList = idDomainsList;
    }

    public void setIdDomain(int idDomain) {
        this.idDomain = idDomain;
    }

    public int getIdDomain() {
        return idDomain;
    }

    public String getDomainString() {
        return domainString;
    }

    public int getIdDomainsList() {
        return idDomainsList;
    }

    @Override
    public int compareTo(Domain o) {
        return idDomain - o.idDomain;
    }

}
