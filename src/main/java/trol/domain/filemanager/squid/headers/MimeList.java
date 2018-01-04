package trol.domain.filemanager.squid.headers;

import trol.domain.filemanager.FilePaths;
import trol.domain.trol_api.model.Header;
import trol.domain.trol_api.model.TransmissionType;
import trol.domain.util.FileHelper;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MimeList {

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
        String time = (tt.getIsTimed()) ? tt.getTimeBegin().getHour()+":"+tt.getTimeBegin().getMinute()+"-"+
                tt.getTimeEnd().getHour()+":"+tt.getTimeEnd().getMinute() : "";
        for(Header i: tt.getHeadersSet()){
            transmissionTypeListString.add("acl "+nameAndId+
                    "Req"+i.getIdHeader()+" req_mime_type -i ^" + i.getHeaderString());
            transmissionTypeListString.add("acl "+nameAndId+
                    "Rep"+i.getIdHeader()+" rep_mime_type -i ^" + i.getHeaderString());
        }
        if(tt.getIsTimed())
            transmissionTypeListString.add("acl "+nameAndId+"Time time MTWHF "+time);
        for(Header i: tt.getHeadersSet()){
            if(tt.getIsTimed()){
                //TODO co z tym all na końcu
                transmissionTypeListString.add("http_access deny "+nameAndId+"Req"+i.getIdHeader()+" "+nameAndId+"Time");
                transmissionTypeListString.add("http_reply_access deny "+nameAndId+"Rep"+i.getIdHeader()+" "+nameAndId+"Time");
                //TODO te z allow tu
            }else {
                transmissionTypeListString.add("http_access deny "+nameAndId+"Req"+i.getIdHeader()+"");
                transmissionTypeListString.add("http_reply_access deny "+nameAndId+"Rep"+i.getIdHeader()+"");
                //TODO te z allow tu
            }
        }
        return transmissionTypeListString;
    }


}
