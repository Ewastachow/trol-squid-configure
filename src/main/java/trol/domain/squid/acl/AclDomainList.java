package trol.domain.squid.acl;

import java.nio.file.Path;
import java.util.List;

public class AclDomainList extends Acl{
    // aclType = DSTDOMAIN
    Path path;
    DomainListType domainListType;
    List<String> content;

    @Override
    public String getConfigString() {
        return super.configString;
    }
}
