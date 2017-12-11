package trol.domain.trol_api.domain;

import java.time.LocalTime;
import java.util.List;

public class DomainsFile {
    String name;
    boolean isTimed;
    LocalTime timeBegin;
    LocalTime timeEnd;
    Mode mode;
    List<String> domainList;
}
