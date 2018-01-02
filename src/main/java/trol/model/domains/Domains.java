package trol.model.domains;

import java.util.ArrayList;
import java.util.List;

public class Domains {
    private List<DomainsList> domainsLists;

    public Domains(){
        domainsLists = new ArrayList<>();
    }

    public List<DomainsList> getDomainsLists() {
        return domainsLists;
    }

    public void setDomainsLists(List<DomainsList> domainsLists) {
        this.domainsLists = domainsLists;
    }
}
