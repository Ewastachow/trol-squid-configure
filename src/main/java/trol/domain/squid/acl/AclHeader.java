package trol.domain.squid.acl;

public class AclHeader extends Acl {
    // typ = REQ / REP
    String blockedHeader;

    @Override
    public String getConfigString() {
        return super.configString;
    }
}
