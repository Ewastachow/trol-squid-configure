package trol.domain.trol_api.model;

import trol.domain.database_models.DomainsListsEntity;

import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

public class DomainsList {

    private int idDomainsList;
    private String domainsListName;
    private byte isActive;
    private byte isBlack;
    private byte isTimed;
    private Time timeBegin;
    private Time timeEnd;
    private Set<Domain> domainsSet;

    public DomainsList() {
        timeBegin = new Time();
    }

    public DomainsList(DomainsListsEntity entity) {
        domainsSet = new HashSet<>();
        idDomainsList = entity.getIdDomainsList();
        domainsListName = entity.getDomainsListName();
        isActive = entity.getIsActive();
        isBlack = entity.getIsBlack();
        isTimed = entity.getIsTimed();
        timeBegin = entity.getTimeBegin();
        timeEnd = entity.getTimeEnd();
        entity.getDomainsEntitySet().forEach(e -> domainsSet.add(new Domain(e)));
    }

    public int getIdDomainsList() {
        return idDomainsList;
    }

    public String getDomainsListName() {
        return domainsListName;
    }

    public byte getIsActive() {
        return isActive;
    }

    public byte getIsBlack() {
        return isBlack;
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

    public Set<Domain> getDomainsSet() {
        return domainsSet;
    }
}
