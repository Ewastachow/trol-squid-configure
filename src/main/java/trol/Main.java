package trol;

import org.hibernate.Session;
import trol.domain.database_models.WordsEntity;
import trol.domain.database_models.WordsListsEntity;
import trol.domain.trol_api.TrolAPI;
import trol.domain.trol_api.model.Word;
import trol.domain.trol_api.model.WordsList;
import trol.domain.util.HibernateUtil;

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
        foo3();
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

    public static void foo3(){
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

        boolean res = trolAPI.changeWordsListActivityMode(idWordsList, true);

        stringBuilder.append(res);
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
}
