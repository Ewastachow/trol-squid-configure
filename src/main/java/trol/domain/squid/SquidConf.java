package trol.domain.squid;

import trol.domain.squid.HttpAccess.HttpAccess;
import trol.domain.squid.acl.*;
import trol.domain.util.FileHelper;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SquidConf {
    Path path;
    List<String> portsAclList;
    List<Acl> aclList;
    String httpPortString;
    List<HttpAccess> httpAccessList;
    List<String> footer;
    Behavior behavior; //TODO: Co ja do cholery miałam na myśli pisząc tu behavior???

    public SquidConf(String path) throws IOException {
        this.path = Paths.get(path);
        portsAclList = new ArrayList<>();
        aclList = new ArrayList<>();
        httpAccessList = new ArrayList<>();
        footer = new ArrayList<>();
        List<String> fileLines = FileHelper.createLineListFromPath(this.path);
//        fileLines.forEach(e -> createLineObject(FileHelper.createWordsListFromLine(e)));
        for(String i: fileLines)
            createLineObject(FileHelper.createWordsListFromLine(i));
        //TODO Iteracja po liscie http_access i dodawanie referencji do acl i to samo na odwrot
    }

    public void saveToFile() throws IOException {
        //TODO kolejnosc http accesow!!!! zwrocic na to uwage
        List<String> result = new ArrayList<>();
        result.addAll(portsAclList);
        aclList.forEach(e -> result.add(e.getConfigString()));
        result.add(httpPortString);
        httpAccessList.forEach(e -> result.add(e.getConfigString()));
        result.addAll(footer);
        FileHelper.saveStringListAsFile(path,result);
    }

    public void unblockTimeBlockedUsers(List<String> users) {
        //TODO: wyzerowanie czasu zużytego i odbanowanie użytkowników z listy
        //TODO: lub odbanowanie po prostu wszystkich zbanowanych z zużtego czasu bez tej lsity
    }

    public void blockTimeUser(String user) {
        //TODO: pakiet log zdecydował na podstawie logów, że tego użytkownika trzeba zablokować
        //TODO: bo już za długo siedzi na necie
    }

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
