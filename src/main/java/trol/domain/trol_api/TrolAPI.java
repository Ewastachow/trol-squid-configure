package trol.domain.trol_api;

import trol.domain.squid.SquidConf;
import trol.domain.trol_api.domain.DomainsFile;
import trol.domain.trol_api.domain.Mode;
import trol.domain.trol_api.header.Headers;
import trol.domain.trol_api.header.TransmisionType;
import trol.domain.trol_api.word.WordsFile;

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
//###################### Headers #############################

    public List<Headers> getHeadersList() {
        //TODO: Implement
        return null;
    }

    public Headers getHeader(TransmisionType mode) {
        //TODO: Implement
        return null;
    }

    public boolean changeHeaderBlockedMode(boolean block) {
        //TODO: Implement
        return false;
    }

    public boolean changeHeaderTime(TransmisionType mode, LocalTime newTimeBegin, LocalTime newTimeEnd) {
        //TODO: Implement
        return false;
    }

    public boolean createNewHeader() {
        //TODO: Implement
        return false;
    }

    public boolean deleteHeader() {
        //TODO: Implement
        return false;
    }
    //###################### Headers #############################
    // ###################### Users #############################

    // ###################### Users #############################
    // ###################### Words #############################

    public List<WordsFile> getWordsFileList(){
        // Zwraca Listę plików które przechowują blokowane słowa
        //TODO: Implement
        return null;
    }

    public WordsFile getWordsListFromWordsFile(String wordsListName){
        //TODO: Implement
        return null;
    }

    public boolean createNewWordsList(String wordsListName){
        //TODO: Impement
        return false;
    }

    public boolean createWordInList(String wordsListName, String word){
        //TODO: Implement
        return false;
    }

    public boolean deleteWordFromList(String wordsListName, String word){
        //TODO: Implement
        return false;
    }

    public boolean deleteWordsList(String wordsListName){
        //TODO: Implement
        return false;
    }

    public boolean changeBlockModeInWordsList(String wordsListName, boolean block){
        //TODO: Implement
        return false;
    }

    public boolean changeTimeInWordsList(String wordsListName, LocalTime newTimeBegin, LocalTime newTimeEnd){
        //TODO: Implement
        return false;
    }

    // ###################### Words #############################

}
