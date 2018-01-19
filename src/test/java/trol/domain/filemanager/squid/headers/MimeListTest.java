package trol.domain.filemanager.squid.headers;

import org.junit.Assert;
import org.junit.Test;
import trol.model.Header;
import trol.model.TransmissionType;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class MimeListTest {

    private Header createHeader(int id, String mime, int transmissionId){
        Header result = new Header();
        result.setIdHeader(id);
        result.setHeaderString(mime);
        result.setIdTransmissionType(transmissionId);
        return result;
    }

    private TransmissionType createTransmissionType(int id, String transmissionTypeName, boolean isActive, boolean isTimed, Set<Header> set){
        TransmissionType result = new TransmissionType();
        result.setIdTransmissionType(id);
        result.setTransmissionTypeName(transmissionTypeName);
        result.setIsActive(isActive);
        result.setIsTimed(isTimed);
        result.setHeadersSet(set);
        return result;
    }

    @Test
    public void createHeadersListStringTestNotActive() throws Exception {
        Header header1 = createHeader(1,"image/jpg",1);
        Set<Header> headers = new TreeSet<>();
        headers.add(header1);
        TransmissionType transmissionType = createTransmissionType(1,"TestTT",false,false,headers);
        List<TransmissionType> transmissionTypeList = new ArrayList<>();
        transmissionTypeList.add(transmissionType);
        List<String> created = MimeList.createHeadersListString(transmissionTypeList);
        List<String> expected = new ArrayList<>();
        Assert.assertEquals("Empty List for not active transmission type", created,expected);
    }

    @Test
    public void createHeadersListStringTestOneActive() throws Exception {
        Header header1 = createHeader(1,"image/jpg",1);
        Set<Header> headers = new TreeSet<>();
        headers.add(header1);
        TransmissionType transmissionType = createTransmissionType(1,"TestTT",true,false,headers);
        List<TransmissionType> transmissionTypeList = new ArrayList<>();
        transmissionTypeList.add(transmissionType);
        List<String> created = MimeList.createHeadersListString(transmissionTypeList);
        List<String> expected = new ArrayList<>();
        expected.add("acl TestTT1Req req_mime_type -i ^image/jpg$");
        expected.add("acl TestTT1Req req_mime_type -i image/jpg");
        expected.add("acl TestTT1Rep rep_mime_type -i ^image/jpg$");
        expected.add("acl TestTT1Rep rep_mime_type -i image/jpg");
        expected.add("http_access deny TestTT1Req"+" all");
        expected.add("http_reply_access deny TestTT1Rep"+" all");
        Assert.assertEquals("Empty List for not active transmission type", created,expected);
    }

}