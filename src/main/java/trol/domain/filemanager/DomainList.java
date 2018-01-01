package trol.domain.filemanager;

import trol.domain.trol_api.model.DomainsList;

import java.nio.file.Path;
import java.nio.file.Paths;

public class DomainList {
    Path path;
    DomainsList domainsList;

    public DomainList(DomainsList domainsList) {
        this.domainsList = domainsList;
        path = Paths.get(FilePaths.DOMAINS_LISTS_PATH+domainsList.getDomainsListName()+".acl");
    }

    public DomainList(Path path, DomainsList domainsList) {
        this.path = path;
        this.domainsList = domainsList;
    }

    public void saveFile(){
        //TODO Implement
    }
}
