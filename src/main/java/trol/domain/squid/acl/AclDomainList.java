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

    public AclDomainList(List<String> words) throws IOException {
        //TODO: co jak na początku jest hash? wtedy pewnie bd words od innych wartości np, parametr index start,
        //TODO: i wteedy przesuniemy o 1 czyli bd words.get(1+param) gdzie param to bd 0 - niezakomentowany lub 1 - zakomentowany
        //TODO: Implement, czy powinno rzucac wyjątek jak coś bd nie tak???
        aclName = words.get(1);
        path = Paths.get(FileHelper.removeQuotationMarks(words.get(3)));
        content = FileHelper.createLineListFromPath(path);
        //TODO: filehelper: jedna z metod, żamiast od string to od path
        //TODO: resztę ustawiamy po wystąpieniu w w http-access
        //TODO: czy dodać tu też referencję do http_ACCESS
    }

    // Konstruktor jeżeli modyfikujemy w programie

    @Override
    public String getConfigString() {
        return ((isCommented) ? "# " : "") + "acl " + aclName + " dstdomain \"" + path.toString() +"\"" ;
    }
}
