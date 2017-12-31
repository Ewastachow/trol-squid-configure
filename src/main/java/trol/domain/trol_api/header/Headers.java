package trol.domain.trol_api.header;

import java.time.LocalTime;

public class Headers {
    TransmissionType transmissionType;
    boolean isTimed;
    LocalTime timeBegin;
    LocalTime timeEnd;
    boolean isBlocked;
}
