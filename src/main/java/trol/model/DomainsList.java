package trol.model;

import org.hibernate.validator.constraints.Length;
import trol.blocking.database_models.DomainsListsEntity;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.Set;
import java.util.TreeSet;

public class DomainsList {

    private int idDomainsList;
    @Length(min = 1, max = 100, message = "Length must between 1 and 100")
    private String domainsListName;
    private boolean isActive;
    private boolean isBlack;
    private boolean isTimed;
    @NotNull(message = "Must not be null")
    private LocalTime timeBegin;
    @NotNull(message = "Must not be null")
    private LocalTime timeEnd;
    private Set<Domain> domainsSet;

    public DomainsList() {
        isActive = false;
        isBlack = true;
        isTimed = false;
        timeBegin = LocalTime.MIN;
        timeEnd = LocalTime.MAX;
        domainsSet = new TreeSet<>();
    }

    public DomainsList(DomainsListsEntity entity) {
        domainsSet = new TreeSet<>();
        idDomainsList = entity.getIdDomainsList();
        domainsListName = entity.getDomainsListName();
        isActive = entity.getIsActive() == 1;
        isBlack = entity.getIsBlack() == 1;
        isTimed = entity.getIsTimed() == 1;
        timeBegin = entity.getTimeBegin().toLocalTime();
        timeEnd = entity.getTimeEnd().toLocalTime();
        entity.getDomainsEntitySet().forEach(e -> domainsSet.add(new Domain(e)));
    }

    public void setIdDomainsList(int idDomainsList) {
        this.idDomainsList = idDomainsList;
    }

    public void setDomainsListName(String domainsListName) {
        this.domainsListName = domainsListName;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void setIsBlack(boolean isBlack) {
        this.isBlack = isBlack;
    }

    public void setIsTimed(boolean isTimed) {
        this.isTimed = isTimed;
    }

    public void setTimeBegin(LocalTime timeBegin) {
        this.timeBegin = timeBegin;
    }

    public void setTimeEnd(LocalTime timeEnd) {
        this.timeEnd = timeEnd;
    }

    public int getIdDomainsList() {
        return idDomainsList;
    }

    public String getDomainsListName() {
        return domainsListName;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public boolean getIsBlack() {
        return isBlack;
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

    public Set<Domain> getDomainsSet() {
        return domainsSet;
    }

    public void setDomainsSet(Set<Domain> domainsSet) {
        this.domainsSet = domainsSet;
    }
}
