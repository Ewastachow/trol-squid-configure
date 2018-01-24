package trol.domain.filemanager;

public class FilePaths {

    public static String DANSGUARDIAN_LISTS_PATH = "src/main/resources/trol/domain/filemanager/test/";
//    public static String DANSGUARDIAN_LISTS_PATH = "/etc/dansguardian/lists/";
    public static String DANSGUARDIAN_PHRASE_INCLUDE_LIST_PATH = DANSGUARDIAN_LISTS_PATH+"bannedphraselist";
    public static String DANSGUARDIAN_PHRASE_LISTS_PATH = DANSGUARDIAN_LISTS_PATH+"phraselists/";
    public static String DANSGUARDIAN_SITE_BLACK_INCLUDE_LIST_PATH = DANSGUARDIAN_LISTS_PATH+"bannedsitelist";
    public static String DANSGUARDIAN_SITE_WHITE_INCLUDE_LIST_PATH = DANSGUARDIAN_LISTS_PATH+"exceptionsitelist";
    public static String DANSGUARDIAN_SITE_LISTS_PATH = DANSGUARDIAN_LISTS_PATH+"blacklists/";
    public static String DANSGUARDIAN_MIME_PATH = DANSGUARDIAN_LISTS_PATH+"/bannedmimetypelist";
    public static String DANSGUARDIAN_USER_PATH = DANSGUARDIAN_LISTS_PATH+"bannediplist";

    public static String SQUID_CONFIGURE_PATH = "src/main/resources/trol/domain/filemanager/test/squid.conf";
//    public static String SQUID_CONFIGURE_PATH = "/etc/squid/squid.conf";
    public static String SQUID_HEADER_CONFIGURATION_PATH = "src/main/resources/trol/domain/filemanager/squidHeader.conf";
    public static String SQUID_FOOTER_CONFIGURATION_PATH = "src/main/resources/trol/domain/filemanager/squidFooter.conf";

    public static String DANSGUARDIAN_ACCESS_LOGS = "/var/log/dansguardian/access.log";
}
