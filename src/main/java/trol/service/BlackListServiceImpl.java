package trol.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("blackListSerevice")
public class BlackListServiceImpl implements DomainsService {
    List<String> blacklist = getFakeList();


    private List<String> getFakeList(){
        List<String> fakeList = new ArrayList<>();
        fakeList.add(".facebook.com");
        fakeList.add(".google.com");
        return fakeList;
    }

    @Override
    public List<String> getDomainsList() {
        return blacklist;
    }

    @Override
    public void addDomain(String domain){
        blacklist.add(domain);
    }

    @Override
    public void deleteDomain(String domain) {
        blacklist.remove(domain);
    }

    @Override
    public void replaceDomain(String oldDomain, String newDomain) {
        blacklist.set(blacklist.indexOf(oldDomain),newDomain);
    }


}
