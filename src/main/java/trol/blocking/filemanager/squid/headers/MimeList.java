package trol.blocking.filemanager.squid.headers;

import trol.model.Header;
import trol.model.TransmissionType;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MimeList {

    /**
     * Creates List with part of squid.conf file which is responsible for blocking headers
     * @param transmissionTypeList
     * @return List with file lines
     * @throws IOException
     */
    public static List<String> createHeadersListString(List<TransmissionType> transmissionTypeList) throws IOException {
        List<String> headersListString = new ArrayList<>();
        for(TransmissionType i: transmissionTypeList)
            if(i.getIsActive())
                headersListString.addAll(createTransmissionTypeListString(i));
        return headersListString;
    }

    private static List<String> createTransmissionTypeListString(TransmissionType tt){
        List<String> transmissionTypeListString = new ArrayList<>();
        String nameAndId = tt.getTransmissionTypeName()+tt.getIdTransmissionType();
        String time = (tt.getIsTimed()) ? createTimeString(tt.getTimeBegin(), tt.getTimeEnd()) : "";
        for(Header i: tt.getHeadersSet()){
            transmissionTypeListString.add("acl "+nameAndId+
                    "Req"+" req_mime_type -i ^" + i.getHeaderString()+"$");
            transmissionTypeListString.add("acl "+nameAndId+
                    "Req"+" req_mime_type -i " + i.getHeaderString()+"");
            transmissionTypeListString.add("acl "+nameAndId+
                    "Rep"+" rep_mime_type -i ^" + i.getHeaderString()+"$");
            transmissionTypeListString.add("acl "+nameAndId+
                    "Rep"+" rep_mime_type -i " + i.getHeaderString()+"");
        }
        if(tt.getIsTimed()){
            transmissionTypeListString.add("acl "+nameAndId+"Time time SMTWHFA "+time);
            transmissionTypeListString.add("http_access deny "+nameAndId+"Req"+" "+nameAndId+"Time all");
            transmissionTypeListString.add("http_reply_access deny "+nameAndId+"Rep"+" "+nameAndId+"Time all");
            transmissionTypeListString.add("http_access allow "+nameAndId+"Req"+" all");
            transmissionTypeListString.add("http_reply_access allow "+nameAndId+"Rep"+" all");
        }else {
            transmissionTypeListString.add("http_access deny "+nameAndId+"Req"+" all");
            transmissionTypeListString.add("http_reply_access deny "+nameAndId+"Rep"+" all");
        }
        return transmissionTypeListString;
    }

    private static String createTimeString(LocalTime beginTime, LocalTime endTime){
        StringBuilder sb = new StringBuilder();
        sb.append((beginTime.getHour() >= 10) ? beginTime.getHour() : ("0"+beginTime.getHour()));
        sb.append(":");
        sb.append((beginTime.getMinute() >= 10) ? beginTime.getMinute() : ("0"+beginTime.getMinute()));
        sb.append("-");
        sb.append((endTime.getHour() >= 10) ? endTime.getHour() : ("0"+endTime.getHour()));
        sb.append(":");
        sb.append((endTime.getMinute() >= 10) ? endTime.getMinute() : ("0"+endTime.getMinute()));
        return sb.toString();
    }


}
