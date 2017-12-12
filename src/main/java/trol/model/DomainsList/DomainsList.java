package trol.model.DomainsList;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class DomainsList {
    @NotNull
    @Size(min =1)
    private String newDomain;

    private List<String> domains;

    public DomainsList() {
        domains = new ArrayList<>();
    }

    public DomainsList(List<String> domains) {
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
