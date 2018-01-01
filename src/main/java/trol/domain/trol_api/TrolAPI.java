package trol.domain.trol_api;

import org.hibernate.Session;
import org.hibernate.query.Query;
import trol.domain.database_models.*;
import trol.domain.trol_api.model.*;
import trol.domain.util.HibernateUtil;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class TrolAPI {

//###################### DOMAINS #############################

    public List<DomainsList> getDomainsListsList(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        List<DomainsListsEntity> domainsListsEntityList = session.createQuery("FROM DomainsListsEntity").list();
        List<DomainsList> result = new ArrayList<>();
        domainsListsEntityList.forEach(e -> result.add(new DomainsList(e)));

        session.getTransaction().commit();
        return result;
    }

    //INFO @Dzieniu DomainsListsEntity zawiera w sb pole Set<Domains> - wystarczy wywołac getDomainsEntitySet()
    public DomainsList getDomainsList(int domainsListId){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        List<DomainsListsEntity> domainsListsEntityList = session.createQuery("FROM DomainsListsEntity WHERE idDomainsList = :domainsListId").setParameter("domainsListId",domainsListId).list();
        DomainsList result = new DomainsList(domainsListsEntityList.get(0));

        session.getTransaction().commit();
        return result;
    }

    public boolean createNewDomainsList(String domainsListName){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        DomainsListsEntity domainsListsEntity = new DomainsListsEntity();
        domainsListsEntity.setDomainsListName(domainsListName);
        domainsListsEntity.setIsActive((byte) 0);
        domainsListsEntity.setIsBlack((byte) 1);
        domainsListsEntity.setIsTimed((byte) 0);
        session.save(domainsListsEntity);

        session.getTransaction().commit();
        //TODO zrobic void i rzucac wyjatek
        return true;
    }

    public boolean addDomainToDomainsList(int domainsListId, String domainString){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        List<DomainsListsEntity> domainsListsEntityList = session.createQuery("FROM DomainsListsEntity WHERE idDomainsList = :domainsListId").setParameter("domainsListId",domainsListId).list();
        DomainsListsEntity domainsListsEntity = domainsListsEntityList.get(0);

        DomainsEntity domainsEntity = new DomainsEntity();
        domainsEntity.setDomainString(domainString);
        domainsEntity.setIdDomainsList(domainsListsEntity);
        session.save(domainsEntity);

        session.getTransaction().commit();
        //TODO zrobic void i rzucac wyjatek
        return true;
    }

    public boolean deleteDomainFromDomainsList(int domainsListId, int domainId){
        //TODO: Implement
        return false;
    }

    public boolean deleteDomainsList(int domainsListId){
        //TODO: Implement
        return false;
    }

    public boolean changeDomainsListMode(int domainsListId, Mode newMode){
        //TODO need to change isActive & isBlack
        //TODO: Implement
        return false;
    }

    public boolean changeDomainsListTime(int domainsListId, Time newTimeBegin, Time newTimeEnd){
        //TODO: Implement
        return false;
    }

//###################### DOMAINS #############################

//###################### Headers #############################

    public List<TransmissionType> getTransmissionTypeList() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        List<TransmissionTypesEntity> transmissionTypesEntityList = session.createQuery("FROM TransmissionTypesEntity ").list();
        List<TransmissionType> result = new ArrayList<>();
        transmissionTypesEntityList.forEach(e -> result.add(new TransmissionType(e)));

        session.getTransaction().commit();
        return result;
    }

    public TransmissionType getTransmissionType(int transmisionTypeId){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        List<TransmissionTypesEntity> transmissionTypesEntityList = session.createQuery("FROM TransmissionTypesEntity WHERE idTransmissionType = :transmisionTypeId").setParameter("transmisionTypeId",transmisionTypeId).list();
        TransmissionType result = new TransmissionType(transmissionTypesEntityList.get(0));

        session.getTransaction().commit();
        return result;
    }

    public boolean changeTransmissionTypeActivityMode(int transmisionTypeId, boolean isActive) {
        //TODO: Implement
        return false;
    }

    public boolean changeTransmissionTypeTime(int transmisionTypeId, Time newTimeBegin, Time newTimeEnd) {
        //TODO: Implement
        return false;
    }

//###################### Headers #############################

//###################### Users #############################

    public List<User> getUsersList() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        List<UserEntity> userEntityList = session.createQuery("FROM UserEntity ").list();
        List<User> result = new ArrayList<>();
        userEntityList.forEach(e -> result.add(new User(e)));

        session.getTransaction().commit();
        return result;
    }

    public User getUser(int userId){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        List<UserEntity> userEntityList = session.createQuery("FROM UserEntity WHERE idUser = :userId").setParameter("userId",userId).list();
        User result = new User(userEntityList.get(0));

        session.getTransaction().commit();
        return result;
    }

    public boolean createUser(String addressIp){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        UserEntity userEntity = new UserEntity();
        userEntity.setUserIp(addressIp);
        userEntity.setIsActive((byte) 0);
        userEntity.setIsTimed((byte) 0);
        userEntity.setHasDuration((byte) 0);
        session.save(userEntity);

        session.getTransaction().commit();
        //TODO zrobic void i rzucac wyjatek
        return true;
    }

    public boolean deleteUser(int userId){
        //TODO: Implement
        return false;
    }

    public boolean changeUserActivityMode(int userId, boolean isActive) {
        //TODO: Implement
        return false;
    }

    public boolean changeUserTimed(int userId, boolean isTimed){
        //TODO: Implement, jesli
        return false;
    }

    public boolean changeUserTime(int userId, Time newTimeBegin, Time newTimeEnd) {
        //TODO: Implement
        return false;
    }

    public boolean changeUserDurationMode(int userId, boolean hasDuration){
        //TODO: Implement - used na 0 ; has duration na true
        //TODO moze zmieniac booleany i wywolywac changeUserDurationTime
        //TODO jesli duration jest null to musimy ustawic
        return false;
    }

    public boolean changeUserDurationTime(int userId, int durationTime){
        //TODO
        return false;
    }

//###################### Users #############################

//###################### Words #############################

    public List<WordsList> getWordsListsList(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        List<WordsListsEntity> wordsListsEntityList = session.createQuery("FROM WordsListsEntity ").list();
        List<WordsList> result = new ArrayList<>();
        wordsListsEntityList.forEach(e -> result.add(new WordsList(e)));

        session.getTransaction().commit();
//        HibernateUtil.getSessionFactory().close();
        return result;
    }

    //INFO @Dzieniu WordsListsEntity zawiera w sb pole Set<Words> - wystarczy wywołac getWordsEntitySet()
    public WordsList getWordsList(int wordsListId){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        List<WordsListsEntity> wordsListsEntityList = session.createQuery("FROM WordsListsEntity WHERE idWordsList = :wordsListId").setParameter("wordsListId",wordsListId).list();
        WordsList result = new WordsList(wordsListsEntityList.get(0));

        session.getTransaction().commit();
//        HibernateUtil.getSessionFactory().close();
        return result;
    }

    public boolean createNewWordsList(String wordsListName){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        WordsListsEntity wordsListsEntity = new WordsListsEntity();
        wordsListsEntity.setWordsListName(wordsListName);
        session.save(wordsListsEntity);

        session.getTransaction().commit();
//        HibernateUtil.getSessionFactory().close();
        //TODO zrobic void i rzucac wyjatek
        return true;
    }

    public boolean addWordToWordsList(int wordsListId, String word){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        List<WordsListsEntity> wordsListsEntityList = session.createQuery("FROM WordsListsEntity WHERE idWordsList = :wordsListId").setParameter("wordsListId",wordsListId).list();
        WordsListsEntity wordsListsEntity = wordsListsEntityList.get(0);

        WordsEntity wordsEntity = new WordsEntity();
        wordsEntity.setWordString(word);
        wordsEntity.setIdWordsList(wordsListsEntity);
        session.save(wordsEntity);

        session.getTransaction().commit();
//        HibernateUtil.getSessionFactory().close();
        //TODO zrobic void i rzucac wyjatek
        return true;
    }

    public boolean deleteWordFromWordsList(int wordsListId, int wordId){
        //TODO: Implement
        return false;
    }

    public boolean deleteWordsList(int wordsListId){
        //TODO: Implement
        return false;
    }

    public boolean changeWordsListActivityMode(int wordsListId, boolean isActive){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        byte activity = isActive ? (byte)1 : (byte)0;
        Query query = session.createQuery("UPDATE WordsListsEntity SET isActive = :activity WHERE idWordsList = :wordsListId");
        query.setParameter("wordsListId",wordsListId);
        query.setParameter("activity", activity);
        int result = query.executeUpdate();

        session.getTransaction().commit();
        return result == 1;
    }



    public boolean changeTimeInWordsList(int wordsListId, Time newTimeBegin, Time newTimeEnd){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Query query = session.createQuery("UPDATE WordsListsEntity SET timeBegin = :newTimeBegin, timeEnd = :newTimeEnd WHERE idWordsList = :wordsListId");
        query.setParameter("wordsListId",wordsListId);
        query.setParameter("newTimeBegin",newTimeBegin);
        query.setParameter("newTimeEnd",newTimeEnd);
        int result = query.executeUpdate();

        session.getTransaction().commit();
        return result == 1;
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
