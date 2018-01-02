package trol.service.headers;

import trol.model.headers.Headers;

import java.util.List;

public interface HeadersService {
    List<Headers> getAllHeadersTypes();
    void updateHeaders(Headers headers) throws Exception;
    Headers getHeaders(long id) throws Exception;
}
