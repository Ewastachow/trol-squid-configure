package trol.domain.squid.HttpAccess;

import trol.domain.squid.ConfigElem;

public class HttpAccess implements ConfigElem{
    String configString;
    HttpAccessType accessType;

    @Override
    public String getConfigString() {
        return configString;
    }
}
