package trol.dao.headers;

import trol.domain.trol_api.model.Header;

public interface HeaderDAO {
    Header getHeader(int headerId);
    int addHeader(Header header);
    void deleteHeader(Header header);
    void updateHeader(Header header);
}
