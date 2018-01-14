package trol.domain.filemanager.dansguardian.mimes;

import trol.domain.trol_api.model.TransmissionType;

import java.io.IOException;
import java.util.List;

public class MimesFileController {

    public static void saveWordsFile(List<TransmissionType> transmissionTypeList) throws IOException {
        MimesList mimesList = new MimesList(transmissionTypeList);
        mimesList.saveFile();
    }
}
