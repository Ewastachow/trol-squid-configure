package trol.dao.headers;

import trol.domain.trol_api.model.TransmissionType;

import java.util.List;

public interface TransmissionTypeDAO {
    List<TransmissionType> getAllTransmissionTypes();
    TransmissionType getTransmissionType(int transmissionTypeId);
    int addTransmissionType(String transmissionTypeName);
    void deleteTransmissionType(int transmissionTypeId);
    void updateTransmissionTypeProperties(TransmissionType transmissionType);
}
