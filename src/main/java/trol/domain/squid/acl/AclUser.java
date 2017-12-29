package trol.domain.squid.acl;

import java.net.Inet4Address;
import java.util.List;

public class AclUser extends Acl{
    private Inet4Address ipAdress;


    public AclUser(List<String> words){
        super();
        aclType = AclType.USER;
        //TODO: Implement, czy powinno rzucac wyjątek jak coś bd nie tak???
    }

    @Override
    public String getConfigString() {
        //TODO: Implement
        return null;
    }
}
