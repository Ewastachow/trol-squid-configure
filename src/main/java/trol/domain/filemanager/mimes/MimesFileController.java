package trol.domain.filemanager.mimes;

import trol.domain.filemanager.words.PhrasesIncludeList;
import trol.domain.filemanager.words.PhrasesList;
import trol.domain.trol_api.model.TransmissionType;
import trol.domain.trol_api.model.WordsList;

import java.io.IOException;
import java.util.List;

public class MimesFileController {

    public static void saveWordsFile(List<TransmissionType> transmissionTypeList) throws IOException {
        MimesList mimesList = new MimesList(transmissionTypeList);
        mimesList.saveFile();
    }
}
