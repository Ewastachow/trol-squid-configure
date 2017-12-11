package trol.service;

import trol.domain.filter.domain_list.*;
import trol.exceptions.IncorrectDomainException;

import java.util.List;

public interface BlackListService {
    List<String> getBlackList();
    void addToBlackList(String domain);
}
