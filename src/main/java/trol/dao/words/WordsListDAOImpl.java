package trol.dao.words;

import org.springframework.stereotype.Repository;
import trol.blocking.database_models.WordsListsEntity;
import trol.model.WordsList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Repository
public class WordsListDAOImpl implements WordsListDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<WordsList> getAllWordsLists() {
        String queryString = "FROM WordsListsEntity";
        List<WordsListsEntity> entities = entityManager.createQuery(queryString).getResultList();
        List<WordsList> result = entities.stream()
                .map(e -> new WordsList(e))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public WordsList getWordsList(int wordsListId) {
        WordsListsEntity entity = entityManager.find(WordsListsEntity.class,wordsListId);
        return new WordsList(entity);
    }

    @Override
    public int addWordsList(String wordsListName) {
        WordsListsEntity entity = new WordsListsEntity(wordsListName);
        entityManager.persist(entity);
        return entity.getIdWordsList();
    }

    @Override
    public void deleteWordsList(int wordsListId) {
        WordsListsEntity entity = entityManager.find(WordsListsEntity.class,wordsListId);
        entity.getWordsEntitySet().clear();
        entityManager.remove(entity);
    }

    @Override
    public void updateWordsListProperties(WordsList wordsList) {
        WordsListsEntity entity = entityManager.find(
                WordsListsEntity.class,
                wordsList.getIdWordsList());
        entity.setWordsListName(wordsList.getWordsListName());
        entity.setIsActive(wordsList.getIsActive() ? (byte) 1 : (byte) 0);
        entity.setIsTimed(wordsList.getIsTimed() ? (byte) 1 : (byte) 0);
        entity.setTimeBegin(Time.valueOf(wordsList.getTimeBegin()));
        entity.setTimeEnd(Time.valueOf(wordsList.getTimeEnd()));
        entityManager.flush();
    }
}
