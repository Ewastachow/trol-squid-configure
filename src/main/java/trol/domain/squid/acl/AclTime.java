package trol.domain.squid.acl;

import java.time.LocalTime;

public class AclTime extends Acl {
    private String aclName;
    private LocalTime timeBegin;
    private LocalTime timeEnd;

    @Override
    public String getConfigString() {
        //TODO: Implement
        return null;
    }

}
