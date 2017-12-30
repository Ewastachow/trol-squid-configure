package trol.domain.database_models;

import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

public class TransmissionTypes {
    private Long idTransmissionType;
    private String transmissionTypeName;
    private Boolean isActive;
    private Boolean isTimed;
    private Time timeBegin;
    private Time timeEnd;

    private Set<Headers> headersSet = new HashSet<>();
}
