package trol.domain.squid.acl;

import trol.domain.squid.util.FileHelper;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class AclDomainList extends Acl{
    // aclType = DSTDOMAIN
    String aclName;
    Path path;
//    DomainListType domainListType; // Czy to tu nam potrzebne?
    List<String> content;

    // Konstruktor do tworzenia jeśli tworzymy podczas zczytywania z pliku
    public AclDomainList(String name, String pathString) {
        aclName = name;
        path = Paths.get(pathString);
        content = FileHelper.createLineListFromFile(pathString);
    }

    // Konstruktor jeżeli modyfikujemy w programie

    @Override
    public String getConfigString() {
        return "acl " + aclName + " dstdomain \"" + path.toString() +"\"" ;
    }
}
