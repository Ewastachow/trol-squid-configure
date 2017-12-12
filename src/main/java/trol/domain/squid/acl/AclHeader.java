package trol.domain.squid.acl;

public class AclHeader extends Acl {
    String aclName;
    String blockedHeader;
    HeaderType headerType;
    String param; //TODO: acl StreamingRequest1 req_mime_type -i ^video/x-ms-asf$ -> co z tym "-i"???


    @Override
    public String getConfigString() {
        //TODO: Implement
        return null;
    }
}
