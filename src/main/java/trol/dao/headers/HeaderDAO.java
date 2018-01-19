package trol.dao.headers;

import trol.model.Header;

public interface HeaderDAO {
    Header getHeader(int headerId);
    int addHeader(Header header);
    void deleteHeader(Header header);
    void updateHeader(Header header);
}
