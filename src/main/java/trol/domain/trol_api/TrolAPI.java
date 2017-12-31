package trol.domain.trol_api;

import org.hibernate.Session;
import trol.domain.database_models.*;
import trol.domain.util.HibernateUtil;

import java.sql.Time;
import java.util.List;

public class TrolAPI {

//###################### DOMAINS #############################

    public List<DomainsListsEntity> getDomainsListsList(){
        // Zwraca Listę plików które przechowują zapisane domany możliwe do blokowania
        //TODO: Check

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        List<DomainsListsEntity> domainsListsEntityList = session.createQuery("FROM DomainsListsEntity").list();

        session.getTransaction().commit();
        return domainsListsEntityList;
    }

    //INFO @Dzieniu DomainsListsEntity zawiera w sb pole Set<Domains> - wystarczy wywołac getDomainsEntitySet()
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
        //TODO: Check

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        List<TransmissionTypesEntity> transmissionTypesEntityList = session.createQuery("FROM TransmissionTypesEntity ").list();

        session.getTransaction().commit();
        return transmissionTypesEntityList;
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
        //TODO: Check

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        List<UserEntity> userEntityList = session.createQuery("FROM UserEntity ").list();

        session.getTransaction().commit();
        return userEntityList;
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
        //TODO: Check

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        List<WordsListsEntity> wordsListsEntityList = session.createQuery("FROM WordsListsEntity ").list();

        session.getTransaction().commit();
        return wordsListsEntityList;
    }

    //INFO @Dzieniu WordsListsEntity zawiera w sb pole Set<Words> - wystarczy wywołac getWordsEntitySet()
    public WordsListsEntity getWordsList(int wordsListId){
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

//###################### SAVE  #############################

    public boolean saveConfiguration(){
        //TODO zapis z bazy do plików
        //TODO reset squida i dansguardiana
        return false;
    }

//###################### SAVE  #############################

}
