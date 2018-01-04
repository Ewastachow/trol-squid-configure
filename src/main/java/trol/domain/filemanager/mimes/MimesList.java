package trol.domain.filemanager.mimes;

import trol.domain.filemanager.FilePaths;
import trol.domain.trol_api.model.TransmissionType;
import trol.domain.trol_api.model.WordsList;
import trol.domain.util.FileHelper;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MimesList {
    Path path;
    List<TransmissionType> transmissionTypeList;

    public MimesList(List<TransmissionType> wordsListList) {
        this.transmissionTypeList = wordsListList;
        path = Paths.get(FilePaths.MIME_PATH);
    }

    public void saveFile() throws IOException {
        FileHelper.saveStringListAsFile(path, generateFileListstring());
    }

    public List<String> generateFileListstring(){
        List<String> wordsFile = new ArrayList<>();
        transmissionTypeList.forEach(e -> {
            if (e.getIsActive() && ((!e.getIsTimed() || (e.getIsTimed() && (LocalTime.now().isBefore(e.getTimeBegin()) || LocalTime.now().isAfter(e.getTimeEnd()))))))
                e.getHeadersSet().forEach(ee -> {
                    wordsFile.add(ee.getHeaderString());
                });

        });
        return wordsFile;
    }
}
