package trol.domain.squid.acl;

import trol.domain.squid.ConfigElem;

import java.util.List;

public abstract class Acl implements ConfigElem{
    boolean isCommented;
//    String configString;
    AclType aclType;

    public static Acl createAclFromTokens(List<String> words){
        //TODO: Implement -> przenieść do ACL - jako konstruktor od tokenów, rzuca wyjątek jak nie ten typ
        if(words.get(2).toLowerCase().equals("dstdomain")){
            //TODO: Co wtedy?
        }else if(words.get(2).toLowerCase().equals("dstdomain")){
            //TODO: Co wtedy?
        }else if(words.get(2).toLowerCase().equals("req_mime_type") || words.get(2).toLowerCase().equals("rep_mime_type")){
            //TODO: Co wtedy?
        }else if(words.get(2).toLowerCase().equals("time")){
            //TODO: Co wtedy?
        }else if(words.get(2).toLowerCase().equals("user")){ //TODO: zmienić user, jaki jest typ dla adresu ip???
            //TODO: Co wtedy?
        }else{
            // TODO  jeżeli nie pasuje zamieniamy spowrotem na stringa i wrzucamy do innych acli jako string
        }
        return null;
    }

}
