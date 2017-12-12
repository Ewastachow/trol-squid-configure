package trol.service;

import trol.domain.filter.domain_list.*;
import trol.exceptions.IncorrectDomainException;

import java.util.List;

public interface DomainsService {
    List<String> getDomainsList();
    void addDomain(String domain);
    void deleteDomain(String domain);
    void replaceDomain(String oldDomain,String newDomain);
}
