package trol.dao.words;

import org.springframework.stereotype.Repository;
import trol.domain.database_models.WordsEntity;
import trol.domain.database_models.WordsListsEntity;
import trol.domain.trol_api.model.Word;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
@Repository
public class WordDAOImpl implements WordDAO{
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Word getWord(int wordId) {
        WordsEntity entity = entityManager.find(WordsEntity.class,wordId);
        return new Word(entity);
    }

    @Override
    public int addWord(Word word) {
        WordsListsEntity listsEntity = entityManager.getReference(
                WordsListsEntity.class,
                word.getIdWordsList()
        );
        WordsEntity entity = new WordsEntity(word.getWordString(),listsEntity);
        entityManager.persist(entity);
        return entity.getIdWord();
    }

    @Override
    public void deleteWord(Word word) {
        //TODO
    }

    @Override
    public void updateWord(Word word) {
        WordsEntity entity = entityManager.find(WordsEntity.class,word.getIdWord());
        entity.setWordString(word.getWordString());
        entityManager.flush();
    }
}
