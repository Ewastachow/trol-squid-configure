package trol.domain.filemanager.squid.users;

import trol.domain.trol_api.model.User;

import java.util.ArrayList;
import java.util.List;

public class IPList {
    public static List<String> createIpsListString(List<User> userList){
        List<String> usersListString = new ArrayList<>();
        for(User i: userList)
            if(i.getIsActive())
                usersListString.addAll(createIpListString(i));
        return usersListString;
    }

    private static List<String> createIpListString(User user){
        List<String> ipListString = new ArrayList<>();

        String nameAndId = "User"+user.getIdUser()+"IP";
        String time = (user.getIsTimed()) ? user.getTimeBegin().getHour()+":"+user.getTimeBegin().getMinute()+"-"+
                user.getTimeEnd().getHour()+":"+user.getTimeEnd().getMinute() : "";
        ipListString.add("acl "+nameAndId+" myip "+user.getUserIp());
        if(user.getIsTimed()){
            ipListString.add("acl "+nameAndId+"Time time MTWHF "+time);
            ipListString.add("http_access allow "+nameAndId+" "+nameAndId+"Time");
            ipListString.add("http_access deny "+nameAndId);
        }else {

            ipListString.add("http_access deny "+nameAndId);
        }

        return ipListString;
    }
}
