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

    public boolean deleteDomain(int domainId){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        String st = "DELETE DomainsEntity WHERE idDomain = :domainId";
        Query query = session.createQuery(st);
        query.setParameter("domainId",domainId);
        int result = query.executeUpdate();

        session.getTransaction().commit();
        return result == 1;
    }

    public boolean deleteDomainsList(int domainsListId){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        String st = "DELETE DomainsListsEntity WHERE idDomainsList = :domainsListId";
        Query query = session.createQuery(st);
        query.setParameter("domainsListId",domainsListId);
        int result = query.executeUpdate();

        session.getTransaction().commit();
        return result == 1;
    }

    public boolean changeDomainsListMode(int domainsListId, Mode newMode){
        //TODO Test
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String st;
        Query query;
        if (newMode.equals(Mode.INACTIVE)){
            byte activity = (byte)0;
            st = "UPDATE DomainsListsEntity SET isActive = :activity WHERE idDomainsList = :domainsListId";
            query = session.createQuery(st);
            query.setParameter("domainsListId",domainsListId);
            query.setParameter("activity", activity);
        }else{
            byte activity = (byte)1;
            byte blacktivity = (newMode.equals(Mode.BLACKLIST) ? (byte)1 : (byte)0);
            st = "UPDATE DomainsListsEntity SET isActive = :activity, isBlack = :blacktivity WHERE idDomainsList = :domainsListId";
            query = session.createQuery(st);
            query.setParameter("domainsListId",domainsListId);
            query.setParameter("activity", activity);
            query.setParameter("blacktivity",blacktivity);
        }
        int result = query.executeUpdate();

        session.getTransaction().commit();
        return result == 1;
    }

    public boolean changeDomainsListTimedMode(int domainsListId, boolean isTimed){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        byte timed = isTimed ? (byte)1 : (byte)0;
        String st = "UPDATE DomainsListsEntity SET isTimed = :timed WHERE idDomainsList = :domainsListId";
        Query query = session.createQuery(st);
        query.setParameter("domainsListId",domainsListId);
        query.setParameter("timed", timed);
        int result = query.executeUpdate();

        session.getTransaction().commit();
        return result == 1;
    }

    public boolean changeDomainsListTime(int domainsListId, Time newTimeBegin, Time newTimeEnd){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        String st = "UPDATE DomainsListsEntity SET timeBegin = :newTimeBegin, timeEnd = :newTimeEnd WHERE idDomainsList = :domainsListId";
        Query query = session.createQuery(st);
        query.setParameter("domainsListId",domainsListId);
        query.setParameter("newTimeBegin",newTimeBegin);
        query.setParameter("newTimeEnd",newTimeEnd);
        int result = query.executeUpdate();

        session.getTransaction().commit();
        return result == 1;
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
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        byte activity = isActive ? (byte)1 : (byte)0;
        String st = "UPDATE TransmissionTypesEntity SET isActive = :activity WHERE idTransmissionType = :transmisionTypeId";
        Query query = session.createQuery(st);
        query.setParameter("transmisionTypeId",transmisionTypeId);
        query.setParameter("activity", activity);
        int result = query.executeUpdate();

        session.getTransaction().commit();
        return result == 1;
    }

    public boolean changeTransmissionTypeTime(int transmisionTypeId, Time newTimeBegin, Time newTimeEnd) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        String st = "UPDATE TransmissionTypesEntity SET timeBegin = :newTimeBegin, timeEnd = :newTimeEnd WHERE idTransmissionType = :transmisionTypeId";
        Query query = session.createQuery(st);
        query.setParameter("transmisionTypeId",transmisionTypeId);
        query.setParameter("newTimeBegin",newTimeBegin);
        query.setParameter("newTimeEnd",newTimeEnd);
        int result = query.executeUpdate();

        session.getTransaction().commit();
        return result == 1;
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
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        String st = "DELETE UserEntity WHERE idUser = :userId";
        Query query = session.createQuery(st);
        query.setParameter("userId",userId);
        int result = query.executeUpdate();

        session.getTransaction().commit();
        return result == 1;
    }

    public boolean changeUserActivityMode(int userId, boolean isActive) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        byte activity = isActive ? (byte)1 : (byte)0;
        String st = "UPDATE UserEntity SET isActive = :activity WHERE idUser = :userId";
        Query query = session.createQuery(st);
        query.setParameter("userId",userId);
        query.setParameter("activity", activity);
        int result = query.executeUpdate();

        session.getTransaction().commit();
        return result == 1;
    }

    public boolean changeUserTimedMode(int userId, boolean isTimed){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        byte timed = isTimed ? (byte)1 : (byte)0;
        String st = "UPDATE UserEntity SET isTimed = :timed WHERE idUser = :userId";
        Query query = session.createQuery(st);
        query.setParameter("userId",userId);
        query.setParameter("timed", timed);
        int result = query.executeUpdate();

        session.getTransaction().commit();
        return result == 1;
    }

    public boolean changeUserTime(int userId, Time newTimeBegin, Time newTimeEnd) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        String st = "UPDATE UserEntity SET timeBegin = :newTimeBegin, timeEnd = :newTimeEnd WHERE idUser = :userId";
        Query query = session.createQuery(st);
        query.setParameter("userId",userId);
        query.setParameter("newTimeBegin",newTimeBegin);
        query.setParameter("newTimeEnd",newTimeEnd);
        int result = query.executeUpdate();

        session.getTransaction().commit();
        return result == 1;
    }

    public boolean changeUserDurationMode(int userId, boolean hasDuration){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        byte activity = hasDuration ? (byte)1 : (byte)0;
        String st = "UPDATE UserEntity SET hasDuration = :activity WHERE idUser = :userId";
        Query query = session.createQuery(st);
        query.setParameter("userId",userId);
        query.setParameter("activity", activity);
        int result = query.executeUpdate();

        session.getTransaction().commit();
        return result == 1;
    }

    public boolean changeUserDurationTime(int userId, int durationTime){
        //TODO Test
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        String st = "UPDATE UserEntity SET durationInterval = :durationTime WHERE idUser = :userId";
        Query query = session.createQuery(st);
        query.setParameter("userId",userId);
        query.setParameter("durationTime", durationTime);
        int result = query.executeUpdate();

        session.getTransaction().commit();
        return result == 1;
    }

    public boolean changeUserUsedTime(int userId, int usedTime){
        //TODO Test
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        String st = "UPDATE UserEntity SET usedTime = :usedTime WHERE idUser = :userId";
        Query query = session.createQuery(st);
        query.setParameter("userId",userId);
        query.setParameter("usedTime", usedTime);
        int result = query.executeUpdate();

        session.getTransaction().commit();
        return result == 1;
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

    public boolean deleteWord(int wordId){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        String st = "DELETE WordsEntity WHERE idWord = :wordId";
        Query query = session.createQuery(st);
        query.setParameter("wordId",wordId);
        int result = query.executeUpdate();

        session.getTransaction().commit();
        return result == 1;
    }

    public boolean deleteWordsList(int wordsListId){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        String st = "DELETE WordsListsEntity WHERE idWordsList = :wordsListId";
        Query query = session.createQuery(st);
        query.setParameter("wordsListId",wordsListId);
        int result = query.executeUpdate();

        session.getTransaction().commit();
        return result == 1;
    }

    public boolean changeWordsListActivityMode(int wordsListId, boolean isActive){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        byte activity = isActive ? (byte)1 : (byte)0;
        String statement = "UPDATE WordsListsEntity SET isActive = :activity WHERE idWordsList = :wordsListId";
        Query query = session.createQuery(statement);
        query.setParameter("wordsListId",wordsListId);
        query.setParameter("activity", activity);
        int result = query.executeUpdate();

        session.getTransaction().commit();
        return result == 1;
    }

    public boolean changeWordsListTimedMode(int wordsListId, boolean isTimed){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        byte timed = isTimed ? (byte)1 : (byte)0;
        String st = "UPDATE WordsListsEntity SET isTimed = :timed WHERE idWordsList = :wordsListId";
        Query query = session.createQuery(st);
        query.setParameter("wordsListId",wordsListId);
        query.setParameter("timed", timed);
        int result = query.executeUpdate();

        session.getTransaction().commit();
        return result == 1;
    }

    public boolean changeTimeInWordsList(int wordsListId, Time newTimeBegin, Time newTimeEnd){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        String statement = "UPDATE WordsListsEntity SET timeBegin = :newTimeBegin, timeEnd = :newTimeEnd WHERE idWordsList = :wordsListId";
        Query query = session.createQuery(statement);
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
