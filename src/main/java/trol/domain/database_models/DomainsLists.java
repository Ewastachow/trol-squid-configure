package trol.domain.database_models;

import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

public class DomainsLists {
    private Long idDomainsList;
    private String domainsListName;
    private Boolean isActive;
    private Boolean isBlack;
    private Boolean isTimed;
    private Time timeBegin;
    private Time timeEnd;

    private Set<Domains> domainsSet = new HashSet<>();
}
