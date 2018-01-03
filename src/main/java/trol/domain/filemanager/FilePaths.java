package trol.domain.filemanager;

public class FilePaths {

    //    public static String DANSGUARDIAN_LISTS_PATH = "/etc/dansguardian/lists";
    public static String DANSGUARDIAN_LISTS_PATH = "src/main/resources/trol/domain/filemanager/";


    public static String PHRASE_INCLUDE_LIST_PATH = DANSGUARDIAN_LISTS_PATH+"bannedphraseslist";
    public static String PHRASE_LISTS_PATH = DANSGUARDIAN_LISTS_PATH+"phraselists/";

    public static String SITE_BLACK_INCLUDE_LIST_PATH = DANSGUARDIAN_LISTS_PATH+"bannedsiteslist";
    public static String SITE_WHITE_INCLUDE_LIST_PATH = DANSGUARDIAN_LISTS_PATH+"exceptionsitelist";
    public static String SITE_LISTS_PATH = DANSGUARDIAN_LISTS_PATH+"sitelists/";

    public static String USER_LIST_PATH = DANSGUARDIAN_LISTS_PATH+"bannediplist";

    public static String MIME_LIST_PATH = DANSGUARDIAN_LISTS_PATH+"bannedmimetypelist";

    public static String SQUID_CONFIGURE_PATH = "src/main/resources/trol/domain/filemanager/squid.conf";
    public static String DOMAINS_LISTS_PATH = "src/main/resources/trol/domain/filemanager/domainlists/";
}
