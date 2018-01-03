package trol.domain.filemanager.trash;

import trol.domain.filemanager.FilePaths;
import trol.domain.trol_api.model.DomainsList;
import trol.domain.util.FileHelper;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public void saveFile() throws IOException {
        List<String> domainsFile = new ArrayList<>();
        domainsList.getDomainsSet().forEach(e -> domainsFile.add(e.getDomainString()));
        FileHelper.saveStringListAsFile(path,domainsFile);
    }
}
