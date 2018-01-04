package trol.domain.filemanager;

public class FilePaths {

        public static String DANSGUARDIAN_LISTS_PATH = "/etc/dansguardian/lists/";
    //public static String DANSGUARDIAN_LISTS_PATH = "src/main/resources/trol/domain/filemanager/test/";

    public static String PHRASE_INCLUDE_LIST_PATH = DANSGUARDIAN_LISTS_PATH+"bannedphraselist";
    public static String PHRASE_LISTS_PATH = DANSGUARDIAN_LISTS_PATH+"phraselists/";

    public static String SITE_BLACK_INCLUDE_LIST_PATH = DANSGUARDIAN_LISTS_PATH+"bannedsitelist";
    public static String SITE_WHITE_INCLUDE_LIST_PATH = DANSGUARDIAN_LISTS_PATH+"exceptionsitelist";
    public static String SITE_LISTS_PATH = DANSGUARDIAN_LISTS_PATH+"downloadmanagers/";

    public static String MIME_PATH = "/etc/dansguardian/lists/bannedmimetypelist";

//    public static String SQUID_CONFIGURE_PATH = "src/main/resources/trol/domain/filemanager/test/squid.conf";

    public static String SQUID_CONFIGURE_PATH = "/etc/squid/squid.conf";

    public static String SQUID_HEADER_CONFIGURATION_PATH = "src/main/resources/trol/domain/filemanager/squidHeader.conf";

    public static String SQUID_FOOTER_CONFIGURATION_PATH = "src/main/resources/trol/domain/filemanager/squidFooter.conf";

}
