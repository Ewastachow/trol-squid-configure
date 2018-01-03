package trol.service.headers;



import trol.domain.trol_api.model.TransmissionType;

import java.util.List;

public interface HeadersService {
    List<TransmissionType> getAllTransmissionTypes();
    TransmissionType getTransmissionType(int transmissionTypeId);
    void updateTransmissionType(TransmissionType transmissionType) throws Exception;
}
