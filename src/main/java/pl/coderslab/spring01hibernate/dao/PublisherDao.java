package pl.coderslab.spring01hibernate.dao;

import org.springframework.stereotype.Component;
import pl.coderslab.spring01hibernate.entity.Publisher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
@Transactional
public class PublisherDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(Publisher entity){
        entityManager.persist(entity);
    }

    public Publisher read(long id){
        return entityManager.find(Publisher.class, id);
    }

    public void update(Publisher entity){
        entityManager.merge(entity);
    }

    public void delete(Publisher entity){
        entityManager.remove(
                entityManager.contains(entity) ? entity : entityManager.merge(entity));
    }

}