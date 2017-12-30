package trol.model.DomainsList;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DomainInList {
    @NotNull
    private String listName;

    //Todo walidacja
    @Size(min = 1)
    private String domain;

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
