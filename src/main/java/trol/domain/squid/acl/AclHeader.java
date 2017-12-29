package trol.domain.squid.acl;

import java.util.List;

public class AclHeader extends Acl {
    private String aclName;
    private String blockedHeader;
    private HeaderType headerType;
    private String param; //TODO: acl StreamingRequest1 req_mime_type -i ^video/x-ms-asf$ -> co z tym "-i"???

    public AclHeader(List<String> words){
        aclName = words.get(1);
        blockedHeader = words.get(4);
        headerType = (words.get(2).equals("req_mime_type")) ? HeaderType.REQ_MIME_TYPE : HeaderType.REP_MIME_TYPE;
        aclType = AclType.HEADER;

        //TODO: Implement, czy powinno rzucac wyjątek jak coś bd nie tak???
    }


    @Override
    public String getConfigString() {
        //TODO: Implement, pamietac o dodaniu -i
        return null;
    }
}
