package trol.domain.squid.acl;

import java.time.LocalTime;
import java.util.List;

public class AclTime extends Acl {
    private String aclName;
    private LocalTime timeBegin;
    private LocalTime timeEnd;

    public AclTime(List<String> words){
        //TODO: Implement, czy powinno rzucac wyjątek jak coś bd nie tak???
        aclName = words.get(1);
        aclType = AclType.TIME;
        //TODO Zczytywanie czasu rozpoczecia i zakonczenia z acl bad_urls_time time MTWHF 12:30-1:30

    }



    @Override
    public String getConfigString() {
        //TODO: Implement
        return null;
    }

}
