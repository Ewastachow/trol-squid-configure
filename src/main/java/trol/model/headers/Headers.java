package trol.model.headers;

import trol.domain.trol_api.header.TransmissionType;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;

public class Headers {
    @NotNull
    private TransmissionType transmissionType;
    private boolean isTimed;
    private LocalTime timeBegin;
    private LocalTime timeEnd;
    private boolean isActive;

    public TransmissionType getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(TransmissionType transmissionType) {
        this.transmissionType = transmissionType;
    }

    public boolean getIsTimed() {
        return isTimed;
    }

    public void setIsTimed(boolean isTimed) {
        this.isTimed = isTimed;
    }

    public LocalTime getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(LocalTime timeBegin) {
        this.timeBegin = timeBegin;
    }

    public LocalTime getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(LocalTime timeEnd) {
        this.timeEnd = timeEnd;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
}
