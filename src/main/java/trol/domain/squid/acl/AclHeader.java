package trol.domain.squid.acl;

import java.util.List;

public class AclHeader extends Acl {
    private String aclName;
    private String blockedHeader;
    private HeaderType headerType;
    private String param; //TODO: acl StreamingRequest1 req_mime_type -i ^video/x-ms-asf$ -> co z tym "-i"???

    public AclHeader(List<String> words){
        //TODO: Implement, czy powinno rzucac wyjątek jak coś bd nie tak???
    }


    @Override
    public String getConfigString() {
        //TODO: Implement
        return null;
    }
}
