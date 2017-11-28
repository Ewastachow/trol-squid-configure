package trol.filter.domain_list;

import com.sun.org.apache.xalan.internal.xsltc.DOM;

import java.util.ArrayList;
import java.util.List;

public class DomainList {

    private List<String> domainList;
    private String path;

    public DomainList(String path) {
        this.domainList = new ArrayList<String>();
        this.path = path;
    }

    public String toString(){
        //TODO: Implement
        return null;
    }

    public DomainList fromString(String string) {
        //TODO: Implement
        return null;
    }

    public void toFile(){
        //TODO: Implement
    }

    public DomainList fromFile(){
        //TODO: Implement
        return null;
    }

    public DomainList addDomain(String domain){
        //TODO: Implement
        return null;
    }

    public DomainList removeDomain(String domain){
        //TODO: Implement
        return null;
    }

    //TODO: Wygenerowac przciaznie dla clonowania
}
