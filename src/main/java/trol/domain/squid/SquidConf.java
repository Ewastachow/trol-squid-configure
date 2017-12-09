package trol.domain.squid;

import trol.domain.squid.HttpAccess.HttpAccess;
import trol.domain.squid.acl.Acl;

import java.nio.file.Path;
import java.util.List;

public class SquidConf {
    Path path;
    List<String> portsAclList;
    List<Acl> aclList;
    String port;
    List<HttpAccess> httpAccessList;
    List<String> footer;
    Behavior behavior;



}
