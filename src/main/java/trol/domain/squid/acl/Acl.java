package trol.domain.squid.acl;

import trol.domain.squid.ConfigElem;
import trol.domain.squid.HttpAccess.HttpAccess;

import java.util.ArrayList;
import java.util.List;

public abstract class Acl implements ConfigElem{
    String aclName;
    boolean isCommented;
//    String configString;
    AclType aclType;
    List<HttpAccess> accessesList;

    public Acl() {
        accessesList = new ArrayList<>();
        //TODO dodac super do konstruktorow dziedziczacych
    }

    public String getAclName() {
        return aclName;
    }

    public AclType getAclType() {
        return aclType;
    }
}
