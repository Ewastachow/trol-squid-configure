package trol.domain.squid.acl;

import java.time.LocalTime;

public class AclTime extends Acl {
    String aclName;
    LocalTime timeBegin;
    LocalTime timeEnd;

    @Override
    public String getConfigString() {
        //TODO: Implement
        return null;
    }

}
