package trol.domain.filemanager.headers;

import trol.domain.filemanager.FilePaths;
import trol.domain.trol_api.model.TransmissionType;
import trol.domain.util.FileHelper;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MimeList {
    Path path;
    List<TransmissionType> transmissionTypeList;

    public MimeList(List<TransmissionType> transmissionTypeList) {
        this.transmissionTypeList = transmissionTypeList;
        path = Paths.get(FilePaths.MIME_LIST_PATH);
    }

    public void saveFile() throws IOException {
        FileHelper.saveStringListAsFile(path, generateFileListstring());
    }

    public List<String> generateFileListstring(){
        //TODO includingFile
//        List<String> mimesFile = new ArrayList<>();
//        wordsListList.forEach(e -> {
//            if (e.getIsActive())
//                mimesFile.add(".Include<" + FilePaths.PHRASE_LISTS_PATH + e.getWordsListName() + ">");
//        });
//        return mimesFile;
        return null;
    }


}
