package trol;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import trol.domain.database_models.WordsEntity;
import trol.domain.database_models.WordsListsEntity;
import trol.domain.util.HibernateUtil;
import trol.terminal_interface.App;

import java.util.List;

public class Main {


    public static void printWords() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        List<WordsEntity> wordsEntityList = session.createQuery("FROM WordsEntity").list();
        wordsEntityList.forEach(e ->
            System.out.printf(e.getWordString()));


//        List<Autor> autorzy = session.createQuery("from Autor").list();
//        for(Autor a : autorzy) {
//            System.out.println(a.getId() + "\t" + a.getImie() + "\t" + a.getNazwisko() + "\tksiazki:");
//            for(Ksiazka k : a.getKsiazki()) {
//                System.out.println("\t" + k.getId() + "\t" + k.getTytul());
//            }
//        }

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

//        List<Autor> autorzy = session.createQuery("from Autor").list();
//        for(Autor a : autorzy) {
//            System.out.println(a.getId() + "\t" + a.getImie() + "\t" + a.getNazwisko() + "\tksiazki:");
//            for(Ksiazka k : a.getKsiazki()) {
//                System.out.println("\t" + k.getId() + "\t" + k.getTytul());
//            }
//        }
        session.getTransaction().commit();
    }

    public static void main(String[] args) {
//        SessionFactory sessionFactory;
//        sessionFactory = new Configuration()
//                .configure() // configures settings from hibernate.cfg.xml
//                .buildSessionFactory();
//        Session session = sessionFactory.openSession();
//        session.beginTransaction();


        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

//        Autor autor = new Autor();
//        autor.setImie("Henryk");
//        autor.setNazwisko("Sienkiewicz");
//        session.save(autor);

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





//
//        Ksiazka ksiazka = new Ksiazka();
//        ksiazka.setTytul("W pustyni i w puszczy");
//        ksiazka.setAutor(autor);
//        session.save(ksiazka);

        session.getTransaction().commit();

        System.out.println("Test Hibernate 02");

        System.out.println("\nWords:");
        printWordsLists();

//        System.out.println("\nKsiazki:");
//        printKsiazki();
        session.close();
        HibernateUtil.getSessionFactory().close();
    }
}
