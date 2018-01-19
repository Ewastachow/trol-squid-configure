package trol.dao.domains;

import trol.model.Domain;

public interface DomainDAO {
    Domain getDomain(int domainId);
    int addDomain(Domain domain);
    void deleteDomain(Domain domain);
    void updateDomain(Domain domain);
}
