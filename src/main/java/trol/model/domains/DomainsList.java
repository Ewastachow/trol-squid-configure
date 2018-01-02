package trol.model.domains;

import trol.domain.trol_api.model.Mode;
import trol.model.ListInfo;

import java.util.ArrayList;
import java.util.List;

public class DomainsList {
    private ListInfo info;
    private Mode mode;
    private List<String> domainList;

    public DomainsList(){
        info = new ListInfo();
        mode = Mode.BLACKLIST;
        domainList = new ArrayList<>();
    }

    public ListInfo getInfo() {
        return info;
    }

    public void setInfo(ListInfo info) {
        this.info = info;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public List<String> getDomainList() {
        return domainList;
    }

    public void setDomainList(List<String> domainList) {
        this.domainList = domainList;
    }
}
