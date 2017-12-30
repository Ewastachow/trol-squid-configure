package trol.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class DomainsListOld {
    @NotNull
    @Size(min =1)
    private String newDomain;

    private List<String> domains;

    public DomainsListOld() {
        domains = new ArrayList<>();
    }

    public DomainsListOld(List<String> domains) {
        this.domains = domains;
    }

    public String getNewDomain() {
        return newDomain;
    }

    public void setNewDomain(String newDomain) {
        this.newDomain = newDomain;
    }

    public List<String> getDomains() {
        return domains;
    }

    public void setDomains(List<String> domains) {
        this.domains = domains;
    }
}
