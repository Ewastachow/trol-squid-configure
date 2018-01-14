package trol.domain.filemanager.squid;

import trol.domain.filemanager.FilePaths;
import trol.domain.filemanager.squid.headers.MimeList;
import trol.domain.filemanager.squid.users.IPList;
import trol.domain.trol_api.model.TransmissionType;
import trol.domain.trol_api.model.User;
import trol.domain.util.FileHelper;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SquidFileController {

    public static void saveUsersAndHeadersFile(List<User> userList, List<TransmissionType> transmissionTypeList) throws IOException {
        List<String> squidConfContent = new ArrayList<>();
        squidConfContent.addAll(createHeaderOfFile());
        squidConfContent.addAll(createHeadersPartOfFile(transmissionTypeList));
        squidConfContent.addAll(createUsersPartOfFile(userList));
        squidConfContent.addAll(createFooterOfFile());
        FileHelper.saveStringListAsFile(Paths.get(FilePaths.SQUID_CONFIGURE_PATH),squidConfContent);
    }

    public static void saveHeaderSquidFile(List<TransmissionType> transmissionTypeList) throws IOException {
        List<String> squidConfContent = new ArrayList<>();
        squidConfContent.addAll(createHeaderOfFile());
        squidConfContent.addAll(createHeadersPartOfFile(transmissionTypeList));
        squidConfContent.addAll(createFooterOfFile());
        FileHelper.saveStringListAsFile(Paths.get(FilePaths.SQUID_CONFIGURE_PATH),squidConfContent);
    }

    private static List<String> createHeaderOfFile() throws IOException {
        List<String> header = new ArrayList<>();
        header.addAll(FileHelper.createLineListFromFile(FilePaths.SQUID_HEADER_CONFIGURATION_PATH));
        return header;
    }

    private static List<String> createFooterOfFile() throws IOException {
        List<String> footer = new ArrayList<>();
        footer.addAll(FileHelper.createLineListFromFile(FilePaths.SQUID_FOOTER_CONFIGURATION_PATH));
        return footer;
    }

    private static List<String> createHeadersPartOfFile(List<TransmissionType> transmissionTypeList) throws IOException {
        List<String> headersPartList = new ArrayList<>();
        headersPartList.addAll(MimeList.createHeadersListString(transmissionTypeList));
        return headersPartList;
    }

    private static List<String> createUsersPartOfFile(List<User> userList){
        List<String> usersPartList = new ArrayList<>();
        usersPartList.addAll(IPList.createIpsListString(userList));
        return usersPartList;
    }
}
