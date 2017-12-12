package trol.domain.squid.acl;

import java.time.LocalTime;

public class AclTime extends Acl {
    LocalTime timeBegin;
    LocalTime timeEnd;

    @Override
    public String getConfigString() {
        return super.configString;
    }

}
