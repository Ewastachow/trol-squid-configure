package trol.domain.trol_api;

import trol.domain.squid.SquidConf;
import trol.domain.trol_api.domain.DomainsFile;
import trol.domain.trol_api.domain.Mode;

import java.time.LocalTime;
import java.util.List;

public class TrolAPI {
    SquidConf squidConf;

    public TrolAPI() {
        squidConf = new SquidConf();

    }
//###################### DOMAINS #############################

    public List<DomainsFile> getDomainsFileList(){
        // Zwraca Listę plików które przechowują zapisane domany możliwe do blokowania
        //TODO: Implement
        return null;
    }

    public DomainsFile getDomainsListFromFile(String domainListName){
        //TODO: Implement
        // Zwraca informacje o danej liście domen, czas w jakim jest blokowana ( lub czy wg jest blokowana czasowo), tryb jej blokowania itp
        return null;
    }

    public boolean createNewDomainsList(String domainListName){
        //TODO: Impement
        return false;
    }

    public boolean createNewDomainInList(String domainListName, String domainName){
        //TODO: Implement
        return false;
    }

    public boolean deleteDomainFromList(String domainListName, String domainName){
        //TODO: Implement
        return false;
    }

    public boolean deleteDomainsList(String domainListName){
        //TODO: Implement
        return false;
    }

    public boolean changeModeInDomainList(String domainListName, Mode newMode){
        //TODO: Implement
        return false;
    }

    public boolean changeTimeInDomainList(String domainListName, LocalTime newTimeBegin, LocalTime newTimeEnd){
        //TODO: Implement
        return false;
    }

//###################### DOMAINS #############################




}
