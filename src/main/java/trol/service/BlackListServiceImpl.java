package trol.service;

import org.springframework.stereotype.Service;
import trol.domain.filter.domain_list.DomainList;
import java.util.ArrayList;
import java.util.List;

@Service("blackListSerevice")
public class BlackListServiceImpl implements BlackListService {
    //private DomainList domainList = new DomainList();

    private DomainList getFakeList(){
        List<String> fakeList = new ArrayList<>();
        fakeList.add(".facebook.com");
        fakeList.add(".google.com");
        DomainList domainList = new DomainList(fakeList, "C:\\lista");
        return domainList;
    }

    @Override
    public DomainList getBlackList() {
        //TODO dodac prawdziwa liste i usunac fake
        return getFakeList();
    }
}
