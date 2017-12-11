package trol.service;

import org.springframework.stereotype.Service;
import trol.domain.filter.domain_list.DomainList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service("blackListSerevice")
public class BlackListServiceImpl implements BlackListService {
    List<String> blacklist = getFakeList();


    private List<String> getFakeList(){
        List<String> fakeList = new ArrayList<>();
        fakeList.add(".facebook.com");
        fakeList.add(".google.com");
        return fakeList;
    }

    @Override
    public List<String> getBlackList() {
        return blacklist;
    }

    @Override
    public void addToBlackList(String domain){
        blacklist.add(domain);
    }


}
