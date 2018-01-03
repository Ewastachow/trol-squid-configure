package trol.domain.filemanager.squid;

import trol.domain.filemanager.FilePaths;
import trol.domain.trol_api.model.TransmissionType;
import trol.domain.trol_api.model.User;
import trol.domain.util.FileHelper;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SquidFileController {
    Path path;
    List<User> userList;
    List<TransmissionType> transmissionTypeList;

    public SquidFileController(List<User> userList, List<TransmissionType> transmissionTypeList) {
        path = Paths.get(FilePaths.SQUID_CONFIGURE_PATH);
        this.userList = userList;
        this.transmissionTypeList = transmissionTypeList;
    }

    public void save() throws IOException {
        List<String> squidConfContent = new ArrayList<>();
        squidConfContent.addAll(createHeaderOfFile());
        squidConfContent.addAll(createHeadersPartOfFile());
        squidConfContent.addAll(createUsersPartOfFile());
        squidConfContent.addAll(createFooterOfFile());
        FileHelper.saveStringListAsFile(path,squidConfContent);
    }

    private List<String> createHeaderOfFile(){
        List<String> header = new ArrayList<>();
        //TODO Góra pliku
        return header;
    }

    private List<String> createFooterOfFile(){
        List<String> footer = new ArrayList<>();
        //TODO Dół pliku
        return footer;
    }

    private List<String> createHeadersPartOfFile(){
        List<String> headersPartList = new ArrayList<>();
        //TODO Dół pliku
        return headersPartList;
    }

    private List<String> createUsersPartOfFile(){
        List<String> usersPartList = new ArrayList<>();
        //TODO Dół pliku
        return usersPartList;
    }
}
