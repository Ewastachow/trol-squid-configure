package trol.blocking.filemanager.squid;

import trol.blocking.filemanager.FilePaths;
import trol.blocking.filemanager.squid.headers.MimeList;
import trol.blocking.util.FileHelper;
import trol.model.TransmissionType;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SquidFileController {

    /**
     * Saves squid.conf file including blocked headers
     * @param transmissionTypeList
     * @throws IOException
     */
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
}
