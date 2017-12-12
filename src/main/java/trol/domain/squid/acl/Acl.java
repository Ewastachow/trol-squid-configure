package trol.domain.squid.acl;

import trol.domain.squid.ConfigElem;

public abstract class Acl implements ConfigElem{
    boolean isCommented;
//    String configString;
    AclType aclType;

}
