package trol.domain.squid.acl;

import java.net.Inet4Address;

public class AclUser extends Acl{
    Inet4Address ipAdress;

    @Override
    public String getConfigString() {
        return null;
    }
}
