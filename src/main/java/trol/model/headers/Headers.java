package trol.model.headers;

import trol.domain.trol_api.header.TransmissionType;
import trol.model.ListInfo;

import javax.validation.constraints.NotNull;

public class Headers {
    @NotNull
    private TransmissionType transmissionType;
    private ListInfo info;

    private Headers(TransmissionType transmissionType){
        this.transmissionType = transmissionType;
        info = new ListInfo();
    }

    public ListInfo getInfo() {
        return info;
    }

    public void setInfo(ListInfo info) {
        this.info = info;
    }

    public TransmissionType getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(TransmissionType transmissionType) {
        this.transmissionType = transmissionType;
    }
}
