package trol.domain.squid.acl;

import java.time.LocalTime;
import java.util.List;

public class AclTime extends Acl {
    private String aclName;
    private LocalTime timeBegin;
    private LocalTime timeEnd;

    public AclTime(List<String> words){
        //TODO: Implement, czy powinno rzucac wyjątek jak coś bd nie tak???
    }

    @Override
    public String getConfigString() {
        //TODO: Implement
        return null;
    }

}
