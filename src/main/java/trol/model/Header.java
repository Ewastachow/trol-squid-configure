package trol.model;

import trol.blocking.database_models.HeadersEntity;

public class Header implements Comparable<Header>{
    private int idHeader;
    private String headerString;
    private int idTransmissionType;

    public Header() {
    }

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

    public void setIdHeader(int idHeader) {
        this.idHeader = idHeader;
    }

    public void setHeaderString(String headerString) {
        this.headerString = headerString;
    }

    public void setIdTransmissionType(int idTransmissionType) {
        this.idTransmissionType = idTransmissionType;
    }

    @Override
    public int compareTo(Header o) {
        return idHeader - o.idHeader;
    }
}
