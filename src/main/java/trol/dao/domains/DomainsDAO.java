package trol.dao.domains;

import trol.domain.trol_api.model.Domain;

public interface DomainsDAO {
    Domain getDomain(int domainId);
    int addDomain(Domain domain);
    void deleteDomain(Domain domain);
    void updateDomain(Domain domain);
}
