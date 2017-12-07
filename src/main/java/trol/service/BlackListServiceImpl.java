package trol.service;

import org.springframework.stereotype.Service;
import trol.domain.filter.domain_list.DomainList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service("blackListSerevice")
public class BlackListServiceImpl implements BlackListService {
    //private DomainList domainList = new DomainList();

    private DomainList getFakeList(){
        List<String> fakeList = new ArrayList<>();
        fakeList.add(".facebook.com");
        fakeList.add(".google.com");
        DomainList domainList = null;
        try {
            domainList = new DomainList(fakeList, "/etc/squid/black-list.acl");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return domainList;
    }

    @Override
    public DomainList getBlackList() {
        DomainList blackList = null;
        try {
            blackList = new DomainList("/etc/squid/black-list.acl");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return blackList;
    }
}
