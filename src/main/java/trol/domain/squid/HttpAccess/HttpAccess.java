package trol.domain.squid.HttpAccess;

import trol.domain.squid.ConfigElem;
import trol.domain.squid.acl.Acl;
import trol.domain.squid.acl.AclTime;
import trol.domain.squid.acl.AclType;

import java.util.List;

public class HttpAccess implements ConfigElem{
//    String configString;
    HttpAccessType accessType;
    Acl acl;
    boolean isTimed;
    AclTime time;
    String additionaParam;

    public HttpAccess(List<String> words, List<Acl> aclList){
        accessType = (words.get(1).equals("allow")) ? HttpAccessType.ALLOW : HttpAccessType.DENY;
        aclList.forEach(e -> {
            if(e.getAclName().equals(words.get(2)) && !e.getAclType().equals(AclType.TIME)) acl = e;
        });
        if(words.size()>3 && !acl.getAclType().equals(AclType.HEADER))
            aclList.forEach(e -> {
                if(e.getAclName().equals(words.get(3)) && e.getAclType().equals(AclType.TIME) && e.getClass().equals(AclTime.class)){
                    isTimed = true;
                    time = (AclTime)e;
                }
            });
        else if(words.size()>3 && acl.getAclType().equals(AclType.HEADER))
            additionaParam = words.get(3);
    }

    @Override
    public String getConfigString() {
        //TODO: Implement Duzo bd zalezec od samego acl-a
        return null;
    }
}
