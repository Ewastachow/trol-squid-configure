package trol.service.headers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trol.dao.headers.TransmissionTypeDAO;
import trol.domain.trol_api.model.TransmissionType;

import java.util.List;

@Service
public class HeadersServiceImpl implements HeadersService {
    @Autowired
    TransmissionTypeDAO transmissionTypeDAO;

    @Override
    public List<TransmissionType> getAllTransmissionTypes() {
        return transmissionTypeDAO.getAllTransmissionTypes();
    }

    @Override
    public TransmissionType getTransmissionType(int transmissionTypeId) {
        return transmissionTypeDAO.getTransmissionType(transmissionTypeId);
    }

    @Override
    public void updateTransmissionType(TransmissionType transmissionType) throws Exception {
        transmissionTypeDAO.updateTransmissionTypeProperties(transmissionType);
    }
}
