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
            aclList.add(Acl.createAclFromTokens(words));
        }else if(words.get(0).toLowerCase().equals("http_port")){
            //TODO: Co wtedy? - port = words.toString - tylko trzeba jakoś spacje dodać
        }else if(words.get(0).toLowerCase().equals("http_access") || words.get(0).toLowerCase().equals("http_reply_access") ){
            httpAccessList.add(createHttpAccessFromTokens(words));
        }else{
            //TODO: Jeśli nie pasuje to zamieniamy na stringa i wrzucamy do footer
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
