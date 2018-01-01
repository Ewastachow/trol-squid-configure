package trol.domain.trol_api.model;

import trol.domain.database_models.DomainsEntity;

public class Domain {
    private int idDomain;
    private String domainString;
    private int idDomainsList;

    public Domain(DomainsEntity entity) {
        idDomain = entity.getIdDomain();
        domainString = entity.getDomainString();
        idDomainsList = entity.getIdDomainsList().getIdDomainsList();
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
}
