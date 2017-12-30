package trol.model.AccessControlList;

import trol.model.DomainsList.DomainsList;

import java.util.List;

public class AccessControlList {
    private List<DomainsList> accessControlList;

    public List<DomainsList> getAccessControlList() {
        return accessControlList;
    }

    public void setAccessControlList(List<DomainsList> accessControlList) {
        this.accessControlList = accessControlList;
    }
}
