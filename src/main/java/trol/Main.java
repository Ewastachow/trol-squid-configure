package trol;

import org.hibernate.Session;
import trol.domain.database_models.WordsEntity;
import trol.domain.database_models.WordsListsEntity;
import trol.domain.filemanager.PhrasesList;
import trol.domain.trol_api.TrolAPI;
import trol.domain.trol_api.exception.UnsuccessfulDeletException;
import trol.domain.trol_api.exception.UnsuccessfulModificationException;
import trol.domain.trol_api.model.Word;
import trol.domain.trol_api.model.WordsList;
import trol.domain.util.HibernateUtil;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

public class Main {


    public static void printWords() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<WordsEntity> wordsEntityList = session.createQuery("FROM WordsEntity").list();
        wordsEntityList.forEach(e ->
            System.out.printf(e.getWordString()));
        session.getTransaction().commit();
    }

    public static void printWordsLists() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<WordsListsEntity> wordsListsEntityList = session.createQuery("FROM WordsListsEntity ").list();
        wordsListsEntityList.forEach(e -> {
            System.out.println(e.getWordsListName() + " &&&&&&&&&&& ");
            e.getWordsEntitySet().forEach(ee -> {
                System.out.print(ee.getWordString() + " @@@ ");
            });
        });
        session.getTransaction().commit();
    }

    public static void main(String[] args) {
        try {
            foo4();
        } catch (UnsuccessfulModificationException e) {
            e.printStackTrace();
        }
//        foo6();
    }

    public static void foo1(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        WordsListsEntity wordsListsEntity = new WordsListsEntity();
        wordsListsEntity.setIsActive((byte) 0);
        wordsListsEntity.setIsTimed((byte) 0);
        wordsListsEntity.setWordsListName("Lamolandia");
        session.save(wordsListsEntity);

        WordsEntity wordsEntity1 = new WordsEntity();
        wordsEntity1.setWordString("Lamadfdsfsdfdsfdsf");
        wordsEntity1.setIdWordsList(wordsListsEntity);
        session.save(wordsEntity1);

        WordsEntity wordsEntity2 = new WordsEntity();
        wordsEntity2.setWordString("Alpaka");
        wordsEntity2.setIdWordsList(wordsListsEntity);
        session.save(wordsEntity2);

        session.getTransaction().commit();

        System.out.println("Test Hibernate 02");

        System.out.println("\nWords:");
        printWordsLists();

        session.close();
        HibernateUtil.getSessionFactory().close();
    }

    public static void foo2(){
        TrolAPI trolAPI = new TrolAPI();
        trolAPI.createNewWordsList("Onomatopeja");

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String name = "Onomatopeja";
        int idWordsList = ((WordsListsEntity)session.createQuery("FROM WordsListsEntity WHERE wordsListName = :name").setParameter("name",name).list().get(0)).getIdWordsList();
        session.getTransaction().commit();

        trolAPI.addWordToWordsList(idWordsList, "Word1");
        trolAPI.addWordToWordsList(idWordsList, "Word2");
        trolAPI.addWordToWordsList(idWordsList, "Word3");
        trolAPI.addWordToWordsList(idWordsList, "Word4");
        trolAPI.addWordToWordsList(idWordsList, "Word5");

        List<WordsList> wordsListsEntityList = trolAPI.getWordsListsList();
        WordsList wordsListsEntity = trolAPI.getWordsList(idWordsList);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n\n\n\n");
        stringBuilder.append(" $$$$$$ LISTS $$$$$$ :  ");
        for(WordsList i : wordsListsEntityList){
            stringBuilder.append(i.getWordsListName());
            stringBuilder.append(" @@ ");
        }
        stringBuilder.append("\n");
        stringBuilder.append(" ****** WORDS IN ONOMATOPEJA ****** :  ");
        for(Word i : wordsListsEntity.getWordsSet()){
            stringBuilder.append(i.getWordString());
            stringBuilder.append("------");
        }
        stringBuilder.append("\n\n\n\n");

        System.out.printf(stringBuilder.toString());

        HibernateUtil.getSessionFactory().close();
    }

    public static void foo3() throws UnsuccessfulModificationException {
        TrolAPI trolAPI = new TrolAPI();
        trolAPI.createNewWordsList("Wrzosowisko");

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n\n\n\n");
        List<WordsList> wordsListsEntityList = trolAPI.getWordsListsList();
        stringBuilder.append(" $$$$$$ LISTS $$$$$$ :  ");
        for(WordsList i : wordsListsEntityList){
            stringBuilder.append(i.getWordsListName());
            stringBuilder.append(" ");
            stringBuilder.append(i.getIsActive());
            stringBuilder.append(" @@ ");
        }
        stringBuilder.append("\n");


        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String name = "Wrzosowisko";
        int idWordsList = ((WordsListsEntity)session.createQuery("FROM WordsListsEntity WHERE wordsListName = :name").setParameter("name",name).list().get(0)).getIdWordsList();
        session.getTransaction().commit();

        trolAPI.changeWordsListActivityMode(idWordsList, true);

//        stringBuilder.append(res);
        stringBuilder.append("\n");
        stringBuilder.append(" $$$$$$ LISTS $$$$$$ :  ");
        for(WordsList i : wordsListsEntityList){
            stringBuilder.append(i.getWordsListName());
            stringBuilder.append("  ");
            stringBuilder.append(i.getIsActive());
            stringBuilder.append(" @@ ");
        }
        stringBuilder.append("\n");

        stringBuilder.append("\n\n\n\n");

        System.out.printf(stringBuilder.toString());
        //Wypisuje zle, ale stan zmienia Oo

        HibernateUtil.getSessionFactory().close();
    }

    public static void foo4() throws UnsuccessfulModificationException {
        TrolAPI trolAPI = new TrolAPI();
        int idWordsList = trolAPI.createNewWordsList("Opoka");

//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        session.beginTransaction();
//        String name = "Opoka";
//        int idWordsList = ((WordsListsEntity)session.createQuery("FROM WordsListsEntity WHERE wordsListName = :name").setParameter("name",name).list().get(0)).getIdWordsList();
//        session.getTransaction().commit();

        trolAPI.changeWordsListActivityMode(idWordsList, true);
        trolAPI.changeTimeInWordsList(idWordsList, LocalTime.of(11,20), LocalTime.of(21,02));

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n\n\n\n");
        stringBuilder.append("\n");

        List<WordsList> wordsListsEntityList = trolAPI.getWordsListsList();
        stringBuilder.append(" $$$$$$ LISTS $$$$$$ :  ");
        wordsListsEntityList.forEach(e -> stringBuilder.append(generateWordsListString(e)));
        stringBuilder.append("\n\n\n\n");

        System.out.printf(stringBuilder.toString());

        HibernateUtil.getSessionFactory().close();
    }

    public static void foo5() throws UnsuccessfulDeletException {
        TrolAPI trolAPI = new TrolAPI();
//        trolAPI.createNewWordsList("LamaApla4");
//        trolAPI.createNewWordsList("Alpaka4");
//        trolAPI.createNewWordsList("Oppppp4");

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String name = "Alpaka4";
        int idWordsList = ((WordsListsEntity)session.createQuery("FROM WordsListsEntity WHERE wordsListName = :name").setParameter("name",name).list().get(0)).getIdWordsList();
        session.getTransaction().commit();

//        trolAPI.addWordToWordsList(idWordsList, "Oma");
//        trolAPI.addWordToWordsList(idWordsList,"asas");

        trolAPI.deleteWordsList(idWordsList);
//        boolean res2 = trolAPI.deleteWordsList(1);


        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n\n\n\n");
//        stringBuilder.append(res1);
        stringBuilder.append("\n");
//        stringBuilder.append(res2);
        stringBuilder.append("\n");

        List<WordsList> wordsListsEntityList = trolAPI.getWordsListsList();
        stringBuilder.append(" $$$$$$ LISTS $$$$$$ :  ");
        wordsListsEntityList.forEach(e -> stringBuilder.append(generateWordsListString(e)));
        stringBuilder.append("\n\n\n\n");

        System.out.printf(stringBuilder.toString());

        HibernateUtil.getSessionFactory().close();


    }

    public static void foo6(){
//        TrolAPI trolAPI = new TrolAPI();
//        int idPhrasesListTest = trolAPI.createNewWordsList("PhrasesListTest");
//        trolAPI.addWordToWordsList(idPhrasesListTest,"olga");
//        trolAPI.addWordToWordsList(idPhrasesListTest," olga ");
//        trolAPI.addWordToWordsList(idPhrasesListTest,"oddlga");
//        trolAPI.addWordToWordsList(idPhrasesListTest,"olssga");
//        trolAPI.addWordToWordsList(idPhrasesListTest,"olgaaa");
//
//        WordsList wordsList = trolAPI.getWordsList(idPhrasesListTest);
//        PhrasesList phrasesList = new PhrasesList(wordsList);
////        phrasesList.setPath(Paths.get("C:\\Users\\yevvy\\Documents\\AGH\\Projects\\IO\\trol-squid-configure\\src\\main\\resources\\trol.domain.filemanager\\phraselists\\lama"));
//        try {
//            phrasesList.saveFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    public static String generateWordsListString(WordsList wl){
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("$$$$    ");
        sb.append(wl.getWordsListName());
        sb.append(" @ ");
        sb.append(wl.getIsActive());
        sb.append(" @ ");
        sb.append(wl.getIsTimed());
        sb.append(" @ ");
        sb.append(wl.getTimeBegin());
        sb.append(" @ ");
        sb.append(wl.getTimeEnd());
        sb.append("   $$$$");
        sb.append("\n");
        return sb.toString();
    }
}
