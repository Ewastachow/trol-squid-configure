package trol.domain.trol_api;

import trol.domain.database_models.*;

import java.sql.Time;
import java.util.List;

public class TrolAPI {

    //###################### DOMAINS #############################

    public List<DomainsListsEntity> getDomainsListsList(){
        // Zwraca Listę plików które przechowują zapisane domany możliwe do blokowania
        //TODO: Implement
        return null;
    }

    public DomainsListsEntity getDomainsList(int domainsListIp){
        //TODO: Implement
        // Zwraca informacje o danej liście domen, czas w jakim jest blokowana ( lub czy wg jest blokowana czasowo), tryb jej blokowania itp
        return null;
    }

    public boolean createNewDomainsList(String domainsListName){
        //TODO: Impement
        return false;
    }

    public boolean createNewDomainInDomainsList(int domainsListIp, String domainString){
        //TODO: Implement
        return false;
    }

    public boolean deleteDomainFromDomainsList(int domainsListIp, int domainId){
        //TODO: Implement
        return false;
    }

    public boolean deleteDomainsList(int domainsListIp){
        //TODO: Implement
        return false;
    }

    public boolean changeDomainsListMode(int domainsListIp, Mode newMode){
        //TODO need to change isActive & isBlack
        //TODO: Implement
        return false;
    }

    public boolean changeTimeInDomainsList(int domainsListIp, Time newTimeBegin, Time newTimeEnd){
        //TODO: Implement
        return false;
    }

    //###################### DOMAINS #############################
    //###################### Headers #############################

    public List<TransmissionTypesEntity> getHeadersList() {
        //TODO: Implement
        return null;
    }

    public boolean changeHeaderActivityMode(TransmissionTypesEntity transmisionType, boolean isActive) {
        //TODO: Implement
        return false;
    }

    public boolean changeHeaderTime(TransmissionTypesEntity transmisionType, Time newTimeBegin, Time newTimeEnd) {
        //TODO: Implement
        return false;
    }

    //###################### Headers #############################
    //###################### Users #############################

    public List<UserEntity> getUsersList() {
        //TODO: Implement
        return null;
    }

    public boolean createUser(String addressIp){
        //TODO: Implement
        return false;
    }

    public boolean deleteUser(int userId){
        //TODO: Implement
        return false;
    }

    public boolean changeUserActivityMode(int userId, boolean isActive) {
        //TODO: Implement
        return false;
    }

    public boolean changeUserTime(int userId, Time newTimeBegin, Time newTimeEnd) {
        //TODO: Implement
        return false;
    }

    public boolean setUserDurationMode(int userId, int durationTime){
        //TODO: Implement - used na 0 ; has duration na true
        //TODO moze zmieniac booleany i wywolywac changeUserDurationTime
        return false;
    }

    public boolean removeUserDurationMode(int userId){
        //TODO ustawia hasDuration na false
        return false;
    }

    public boolean changeUserDurationTime(int userId, int durationTime){
        //TODO
        return false;
    }

    //###################### Users #############################
    //###################### Words #############################

    public List<WordsListsEntity> getWordsListsList(){
        // Zwraca Listę plików które przechowują blokowane słowa
        //TODO: Implement
        return null;
    }

    public List<WordsEntity> getWordsListFromWordsFile(int wordsListId){
        //TODO: Implement
        return null;
    }

    public boolean createNewWordsList(String wordsListName){
        //TODO: Impement
        return false;
    }

    public boolean createWordInList(int wordsListId, String word){
        //TODO: Implement
        return false;
    }

    public boolean deleteWordFromList(int wordsListId, int wordId){
        //TODO: Implement
        return false;
    }

    public boolean deleteWordsList(int wordsListId){
        //TODO: Implement
        return false;
    }

    public boolean changeWordsListActivityMode(int wordsListId, boolean isActive){
        //TODO: Implement
        return false;
    }

    public boolean changeTimeInWordsList(int wordsListId, Time newTimeBegin, Time newTimeEnd){
        //TODO: Implement
        return false;
    }
    //###################### Words #############################

}
