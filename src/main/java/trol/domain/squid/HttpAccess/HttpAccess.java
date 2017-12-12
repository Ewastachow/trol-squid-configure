package trol.domain.squid.HttpAccess;

import trol.domain.squid.ConfigElem;
import trol.domain.squid.acl.Acl;
import trol.domain.squid.acl.AclTime;

public class HttpAccess implements ConfigElem{
    String configString;
    HttpAccessType accessType;
    Acl alc;
    boolean isTimed;
    AclTime time;

    @Override
    public String getConfigString() {
        return null;
    }
}
