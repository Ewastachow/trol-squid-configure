package trol.domain.trol_api;

import org.hibernate.Session;
import org.hibernate.query.Query;
import trol.domain.database_models.*;
import trol.domain.trol_api.exception.UnsuccessfulDeletException;
import trol.domain.trol_api.exception.UnsuccessfulModificationException;
import trol.domain.trol_api.model.*;
import trol.domain.util.HibernateUtil;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TrolAPI {

    //Metody CREATE i ADD zwracaja ID tworzonego obiektu

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

    public int createNewDomainsList(String domainsListName){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        DomainsListsEntity domainsListsEntity = new DomainsListsEntity();
        domainsListsEntity.setDomainsListName(domainsListName);
        domainsListsEntity.setIsActive((byte) 0);
        domainsListsEntity.setIsBlack((byte) 1);
        domainsListsEntity.setIsTimed((byte) 0);
        session.save(domainsListsEntity);
        int domainsListId = domainsListsEntity.getIdDomainsList();
        session.getTransaction().commit();
        return domainsListId;
    }

    public int addDomainToDomainsList(int domainsListId, String domainString){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<DomainsListsEntity> domainsListsEntityList = session.createQuery("FROM DomainsListsEntity WHERE idDomainsList = :domainsListId").setParameter("domainsListId",domainsListId).list();
        DomainsListsEntity domainsListsEntity = domainsListsEntityList.get(0);
        DomainsEntity domainsEntity = new DomainsEntity();
        domainsEntity.setDomainString(domainString);
        domainsEntity.setIdDomainsList(domainsListsEntity);
        session.save(domainsEntity);
        int domainId = domainsEntity.getIdDomain();
        session.getTransaction().commit();
        return domainId;
    }

    public void deleteDomain(int domainId) throws UnsuccessfulDeletException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        String st = "DELETE DomainsEntity WHERE idDomain = :domainId";
        Query query = session.createQuery(st);
        query.setParameter("domainId",domainId);
        int result = query.executeUpdate();
        if(result!=1) throw new UnsuccessfulDeletException("TrolAPI delete exception");
        session.getTransaction().commit();
    }

    public void deleteDomainsList(int domainsListId) throws UnsuccessfulDeletException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        String st = "DELETE DomainsListsEntity WHERE idDomainsList = :domainsListId";
        Query query = session.createQuery(st);
        query.setParameter("domainsListId",domainsListId);
        int result = query.executeUpdate();
        if(result!=1) throw new UnsuccessfulDeletException("TrolAPI delete exception");
        session.getTransaction().commit();
    }

    public void changeDomainsListMode(int domainsListId, Mode newMode) throws UnsuccessfulModificationException {
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
        if(result!=1) throw new UnsuccessfulModificationException("TrolAPI modification exception");
        session.getTransaction().commit();
    }

    public void changeDomainsListTimedMode(int domainsListId, boolean isTimed) throws UnsuccessfulModificationException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        byte timed = isTimed ? (byte)1 : (byte)0;
        String st = "UPDATE DomainsListsEntity SET isTimed = :timed WHERE idDomainsList = :domainsListId";
        Query query = session.createQuery(st);
        query.setParameter("domainsListId",domainsListId);
        query.setParameter("timed", timed);
        int result = query.executeUpdate();
        if(result!=1) throw new UnsuccessfulModificationException("TrolAPI modification exception");
        session.getTransaction().commit();
    }

    public void changeDomainsListTime(int domainsListId, LocalTime newLocalTimeBegin, LocalTime newLocalTimeEnd) throws UnsuccessfulModificationException {
        Time newTimeBegin = Time.valueOf(newLocalTimeBegin);
        Time newTimeEnd = Time.valueOf(newLocalTimeEnd);

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        String st = "UPDATE DomainsListsEntity SET timeBegin = :newTimeBegin, timeEnd = :newTimeEnd WHERE idDomainsList = :domainsListId";
        Query query = session.createQuery(st);
        query.setParameter("domainsListId",domainsListId);
        query.setParameter("newTimeBegin",newTimeBegin);
        query.setParameter("newTimeEnd",newTimeEnd);
        int result = query.executeUpdate();
        if(result!=1) throw new UnsuccessfulModificationException("TrolAPI modification exception");
        session.getTransaction().commit();
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

    public void changeTransmissionTypeActivityMode(int transmisionTypeId, boolean isActive) throws UnsuccessfulModificationException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        byte activity = isActive ? (byte)1 : (byte)0;
        String st = "UPDATE TransmissionTypesEntity SET isActive = :activity WHERE idTransmissionType = :transmisionTypeId";
        Query query = session.createQuery(st);
        query.setParameter("transmisionTypeId",transmisionTypeId);
        query.setParameter("activity", activity);
        int result = query.executeUpdate();
        if(result!=1) throw new UnsuccessfulModificationException("TrolAPI modification exception");
        session.getTransaction().commit();
    }

    public void changeTransmissionTypeTime(int transmisionTypeId, LocalTime newLocalTimeBegin, LocalTime newLocalTimeEnd) throws UnsuccessfulModificationException {
        Time newTimeBegin = Time.valueOf(newLocalTimeBegin);
        Time newTimeEnd = Time.valueOf(newLocalTimeEnd);

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        String st = "UPDATE TransmissionTypesEntity SET timeBegin = :newTimeBegin, timeEnd = :newTimeEnd WHERE idTransmissionType = :transmisionTypeId";
        Query query = session.createQuery(st);
        query.setParameter("transmisionTypeId",transmisionTypeId);
        query.setParameter("newTimeBegin",newTimeBegin);
        query.setParameter("newTimeEnd",newTimeEnd);
        int result = query.executeUpdate();
        if(result!=1) throw new UnsuccessfulModificationException("TrolAPI modification exception");
        session.getTransaction().commit();
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

    public int createUser(String addressIp){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        UserEntity userEntity = new UserEntity();
        userEntity.setUserIp(addressIp);
        userEntity.setIsActive((byte) 0);
        userEntity.setIsTimed((byte) 0);
        userEntity.setHasDuration((byte) 0);
        session.save(userEntity);
        int userId = userEntity.getIdUser();
        session.getTransaction().commit();
        return userId;
    }

    public void deleteUser(int userId) throws UnsuccessfulDeletException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        String st = "DELETE UserEntity WHERE idUser = :userId";
        Query query = session.createQuery(st);
        query.setParameter("userId",userId);
        int result = query.executeUpdate();
        if(result!=1) throw new UnsuccessfulDeletException("TrolAPI delete exception");
        session.getTransaction().commit();
    }

    public void changeUserActivityMode(int userId, boolean isActive) throws UnsuccessfulModificationException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        byte activity = isActive ? (byte)1 : (byte)0;
        String st = "UPDATE UserEntity SET isActive = :activity WHERE idUser = :userId";
        Query query = session.createQuery(st);
        query.setParameter("userId",userId);
        query.setParameter("activity", activity);
        int result = query.executeUpdate();
        if(result!=1) throw new UnsuccessfulModificationException("TrolAPI modification exception");
        session.getTransaction().commit();
    }

    public void changeUserTimedMode(int userId, boolean isTimed) throws UnsuccessfulModificationException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        byte timed = isTimed ? (byte)1 : (byte)0;
        String st = "UPDATE UserEntity SET isTimed = :timed WHERE idUser = :userId";
        Query query = session.createQuery(st);
        query.setParameter("userId",userId);
        query.setParameter("timed", timed);
        int result = query.executeUpdate();
        if(result!=1) throw new UnsuccessfulModificationException("TrolAPI modification exception");
        session.getTransaction().commit();
    }

    public void changeUserTime(int userId, LocalTime newLocalTimeBegin, LocalTime newLocalTimeEnd) throws UnsuccessfulModificationException {
        Time newTimeBegin = Time.valueOf(newLocalTimeBegin);
        Time newTimeEnd = Time.valueOf(newLocalTimeEnd);

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        String st = "UPDATE UserEntity SET timeBegin = :newTimeBegin, timeEnd = :newTimeEnd WHERE idUser = :userId";
        Query query = session.createQuery(st);
        query.setParameter("userId",userId);
        query.setParameter("newTimeBegin",newTimeBegin);
        query.setParameter("newTimeEnd",newTimeEnd);
        int result = query.executeUpdate();
        if(result!=1) throw new UnsuccessfulModificationException("TrolAPI modification exception");
        session.getTransaction().commit();
    }

    public void changeUserDurationMode(int userId, boolean hasDuration) throws UnsuccessfulModificationException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        byte activity = hasDuration ? (byte)1 : (byte)0;
        String st = "UPDATE UserEntity SET hasDuration = :activity WHERE idUser = :userId";
        Query query = session.createQuery(st);
        query.setParameter("userId",userId);
        query.setParameter("activity", activity);
        int result = query.executeUpdate();
        if(result!=1) throw new UnsuccessfulModificationException("TrolAPI modification exception");
        session.getTransaction().commit();
    }

    public void changeUserDurationTime(int userId, int durationTime) throws UnsuccessfulModificationException {
        //TODO Test
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        String st = "UPDATE UserEntity SET durationInterval = :durationTime WHERE idUser = :userId";
        Query query = session.createQuery(st);
        query.setParameter("userId",userId);
        query.setParameter("durationTime", durationTime);
        int result = query.executeUpdate();
        if(result!=1) throw new UnsuccessfulModificationException("TrolAPI modification exception");
        session.getTransaction().commit();
    }

    public void changeUserUsedTime(int userId, int usedTime) throws UnsuccessfulModificationException {
        //TODO Test
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        String st = "UPDATE UserEntity SET usedTime = :usedTime WHERE idUser = :userId";
        Query query = session.createQuery(st);
        query.setParameter("userId",userId);
        query.setParameter("usedTime", usedTime);
        int result = query.executeUpdate();
        if(result!=1) throw new UnsuccessfulModificationException("TrolAPI modification exception");
        session.getTransaction().commit();
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

    public int createNewWordsList(String wordsListName){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        WordsListsEntity wordsListsEntity = new WordsListsEntity();
        wordsListsEntity.setWordsListName(wordsListName);
        session.save(wordsListsEntity);
        int wordsListId = wordsListsEntity.getIdWordsList();

        session.getTransaction().commit();
//        HibernateUtil.getSessionFactory().close();
        //TODO zrobic void i rzucac wyjatek
        return wordsListId;
    }

    public int addWordToWordsList(int wordsListId, String word){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        List<WordsListsEntity> wordsListsEntityList = session.createQuery("FROM WordsListsEntity WHERE idWordsList = :wordsListId").setParameter("wordsListId",wordsListId).list();
        WordsListsEntity wordsListsEntity = wordsListsEntityList.get(0);

        WordsEntity wordsEntity = new WordsEntity();
        wordsEntity.setWordString(word);
        wordsEntity.setIdWordsList(wordsListsEntity);
        session.save(wordsEntity);
        int wordId = wordsEntity.getIdWord();
        session.getTransaction().commit();
//        HibernateUtil.getSessionFactory().close();
        //TODO zrobic void i rzucac wyjatek
        return wordId;
    }

    public void deleteWord(int wordId) throws UnsuccessfulDeletException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        String st = "DELETE WordsEntity WHERE idWord = :wordId";
        Query query = session.createQuery(st);
        query.setParameter("wordId",wordId);
        int result = query.executeUpdate();
        if(result!=1) throw new UnsuccessfulDeletException("TrolAPI delete exception");
        session.getTransaction().commit();
    }

    public void deleteWordsList(int wordsListId) throws UnsuccessfulDeletException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        String st = "DELETE WordsListsEntity WHERE idWordsList = :wordsListId";
        Query query = session.createQuery(st);
        query.setParameter("wordsListId",wordsListId);
        int result = query.executeUpdate();
        if(result!=1) throw new UnsuccessfulDeletException("TrolAPI delete exception");
        session.getTransaction().commit();
    }

    public void changeWordsListActivityMode(int wordsListId, boolean isActive) throws UnsuccessfulModificationException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        byte activity = isActive ? (byte)1 : (byte)0;
        String statement = "UPDATE WordsListsEntity SET isActive = :activity WHERE idWordsList = :wordsListId";
        Query query = session.createQuery(statement);
        query.setParameter("wordsListId",wordsListId);
        query.setParameter("activity", activity);
        int result = query.executeUpdate();
        if(result!=1) throw new UnsuccessfulModificationException("TrolAPI modification exception");
        session.getTransaction().commit();
    }

    public void changeWordsListTimedMode(int wordsListId, boolean isTimed) throws UnsuccessfulModificationException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        byte timed = isTimed ? (byte)1 : (byte)0;
        String st = "UPDATE WordsListsEntity SET isTimed = :timed WHERE idWordsList = :wordsListId";
        Query query = session.createQuery(st);
        query.setParameter("wordsListId",wordsListId);
        query.setParameter("timed", timed);
        int result = query.executeUpdate();
        if(result!=1) throw new UnsuccessfulModificationException("TrolAPI modification exception");
        session.getTransaction().commit();
    }

    public void changeTimeInWordsList(int wordsListId, LocalTime newLocalTimeBegin, LocalTime newLocalTimeEnd) throws UnsuccessfulModificationException {
        Time newTimeBegin = Time.valueOf(newLocalTimeBegin);
        Time newTimeEnd = Time.valueOf(newLocalTimeEnd);

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        String statement = "UPDATE WordsListsEntity SET timeBegin = :newTimeBegin, timeEnd = :newTimeEnd WHERE idWordsList = :wordsListId";
        Query query = session.createQuery(statement);
        query.setParameter("wordsListId",wordsListId);
        query.setParameter("newTimeBegin",newTimeBegin);
        query.setParameter("newTimeEnd",newTimeEnd);
        int result = query.executeUpdate();
        if(result!=1) throw new UnsuccessfulModificationException("TrolAPI modification exception");
        session.getTransaction().commit();
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
