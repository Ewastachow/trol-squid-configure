package trol.domain.squid;

import trol.domain.squid.HttpAccess.HttpAccess;
import trol.domain.squid.acl.*;
import trol.domain.squid.util.FileHelper;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class SquidConf {
    Path path;
    List<String> portsAclList;
    List<Acl> aclList;
    String httpPortString;
    List<HttpAccess> httpAccessList;
    List<String> footer;
    Behavior behavior; //TODO: Co ja do cholery miałam na myśli pisząc tu behavior???

// To tak, musimy wczytać plik squid.conf, i po kolei dla każdej linijki, dzielimy na tockeny, w sensie dla każdej linii tworzymy List<String> - czyli listę wyrazów


    private void createLineObject(List<String> words) throws IOException {
        //TODO A co jak pierwszy znak to #????? trzeba coś z tym zrobić !!!!!
        if(words.get(0).toLowerCase().equals("acl")){
            if(words.get(2).toLowerCase().equals("dstdomain")){
                aclList.add(new AclDomainList(words));
            }else if(words.get(2).toLowerCase().equals("req_mime_type") || words.get(2).toLowerCase().equals("rep_mime_type")){
                aclList.add(new AclHeader(words));
            }else if(words.get(2).toLowerCase().equals("time")){
                aclList.add(new AclTime(words));
            }else if(words.get(2).toLowerCase().equals("user")){ //TODO: zmienić user -> Jaki jest typ dla IP???
                aclList.add(new AclUser(words));
            }else{
                portsAclList.add(FileHelper.createStringFromWordsList(words));
            }
        }else if(words.get(0).toLowerCase().equals("http_port")){
            httpPortString = FileHelper.createStringFromWordsList(words);
        }else if(words.get(0).toLowerCase().equals("http_access") || words.get(0).toLowerCase().equals("http_reply_access") ){

            httpAccessList.add(createHttpAccessFromTokens(words));
        }else{
            footer.add(FileHelper.createStringFromWordsList(words));
        }
    }

    private Acl getAclFromAclName(String name){
        //TODO iteruje po liście Acl-ek i zwraca referencję do acl-ki o tej nazwie, jeżeli nie znajdzie to niech np. wyrzuca jakiś nasz exception
        return null;
    }

    private HttpAccess createHttpAccessFromTokens(List<String> words){
        // Konstruktor od words i potem wywołanie iteracji i dodanie referencji do acl
        //TODO: Implement ->  rzuca wyjątek jak nie ten typ- tylko co -, a w funkcji w której było wywołane dodaje te referencje do listy
        return null;
    }

}
