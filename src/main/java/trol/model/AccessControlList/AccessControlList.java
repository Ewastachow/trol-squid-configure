package trol.model.AccessControlList;

import trol.model.DomainsList.DomainsList;

import java.util.ArrayList;
import java.util.List;

public class AccessControlList {
    private List<DomainsList> domainsLists;

    public AccessControlList(){
        domainsLists = new ArrayList<>();
    }

    public List<DomainsList> getDomainsLists() {
        return domainsLists;
    }

    public void setDomainsLists(List<DomainsList> domainsLists) {
        this.domainsLists = domainsLists;
    }
}
