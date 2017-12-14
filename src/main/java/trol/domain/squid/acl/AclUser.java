package trol.domain.squid.acl;

import java.net.Inet4Address;

public class AclUser extends Acl{
    private String aclName;
    private Inet4Address ipAdress;

    @Override
    public String getConfigString() {
        //TODO: Implement
        return null;
    }
}
