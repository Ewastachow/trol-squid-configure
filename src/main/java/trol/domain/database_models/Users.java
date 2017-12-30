package trol.domain.database_models;

import java.sql.Time;

public class Users {
    private Long idUser;
    private String userIP;
    private Boolean isActive;
    private Boolean isTimed;
    private Time timeBegin;
    private Time timeEnd;
    private Boolean hasDuration;
    private Integer durationTime;
    private Integer usedTime;
}
