package trol.domain.squid.acl;

import trol.domain.squid.util.FileHelper;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class AclDomainList extends Acl{
    private String aclName;
    private Path path;
    private DomainListType domainListType;
    private List<String> content;

    // Konstruktor do tworzenia jeśli tworzymy podczas zczytywania z pliku
    public AclDomainList(String name, String pathString) throws IOException {
        aclName = name;
        path = Paths.get(pathString);
        content = FileHelper.createLineListFromFile(pathString);
        domainListType = DomainListType.UNKNOW;
        aclType = AclType.DSTDOMAIN;
        isCommented = true;
    }

    // Konstruktor jeżeli modyfikujemy w programie

    @Override
    public String getConfigString() {
        return "acl " + aclName + " dstdomain \"" + path.toString() +"\"" ;
    }
}
