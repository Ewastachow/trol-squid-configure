package trol.domain.squid.acl;

import java.net.Inet4Address;

public class AclUser extends Acl{
    String aclName;
    Inet4Address ipAdress;

    @Override
    public String getConfigString() {
        //TODO: Implement
        return null;
    }
}
