package trol.domain.trol_api.model;

import trol.domain.database_models.HeadersEntity;

public class Header {
    private int idHeader;
    private String headerString;
    private int idTransmissionType;

    public Header(HeadersEntity entity) {
        idHeader = entity.getIdHeader();
        headerString = entity.getHeaderString();
        idTransmissionType = entity.getIdTransmissionType().getIdTransmissionType();
    }

    public int getIdHeader() {
        return idHeader;
    }

    public String getHeaderString() {
        return headerString;
    }

    public int getIdTransmissionType() {
        return idTransmissionType;
    }
}
