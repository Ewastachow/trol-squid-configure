package trol.domain.filemanager;

import trol.domain.trol_api.model.DomainsList;
import trol.domain.trol_api.model.TransmissionType;
import trol.domain.trol_api.model.User;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class SquidConf {
    Path path;
    List<DomainsList> domainsListList;
    List<TransmissionType> transmissionTypeList;
    List<User> userList;

    public SquidConf(List<DomainsList> domainsListList, List<TransmissionType> transmissionTypeList, List<User> userList) {
        this.domainsListList = domainsListList;
        this.transmissionTypeList = transmissionTypeList;
        this.userList = userList;
        path = Paths.get(FilePaths.SQUID_CONFIGURE_PATH);
    }

    public SquidConf(Path path, List<DomainsList> domainsListList, List<TransmissionType> transmissionTypeList, List<User> userList) {
        this.path = path;
        this.domainsListList = domainsListList;
        this.transmissionTypeList = transmissionTypeList;
        this.userList = userList;
    }

    public void saveFile(){
        //TODO Implement
    }
}
