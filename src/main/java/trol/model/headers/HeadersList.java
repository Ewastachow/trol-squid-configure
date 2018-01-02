package trol.model.headers;

import trol.domain.trol_api.header.TransmissionType;

import java.util.ArrayList;
import java.util.List;

public class HeadersList {
    private List<Headers> headersList;

    public HeadersList() {
        headersList = new ArrayList<>(TransmissionType.values().length);
    }

    public List<Headers> getHeadersList() {
        return headersList;
    }

    public void setHeadersList(List<Headers> headersList) {
        this.headersList = headersList;
    }
}
