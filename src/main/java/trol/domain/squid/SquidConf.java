package trol.domain.squid;

import trol.domain.squid.HttpAccess.HttpAccess;
import trol.domain.squid.acl.Acl;

import java.nio.file.Files;
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

// To tak, musimy wczytać plik squid.conf, i po kolei dla każdej linijki, dzielimy na tockeny, w sensie dla każdej linii tworzymy List<String> - czyli listę wyrazów


    private void createLineObject(List<String> words){
        if(words.get(0).toLowerCase().equals("acl")){
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
        }else if(words.get(0).toLowerCase().equals("http_port")){
            //TODO: Co wtedy?
        }else if(words.get(0).toLowerCase().equals("http_access") || words.get(0).toLowerCase().equals("http_reply_access") ){
            //TODO: Co wtedy?
        }else{
            //TODO: Jeśli nie pasuje to zamieniamy na stringa i wrzucamy do footer
        }
    }

}
