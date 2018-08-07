package pl.coderslab.spring01hibernate.dao;

import org.springframework.stereotype.Component;
import pl.coderslab.spring01hibernate.entity.Author;
import pl.coderslab.spring01hibernate.entity.Book;
import pl.coderslab.spring01hibernate.entity.Publisher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class AuthorDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(Author entity){
        entityManager.persist(entity);
    }

    public void editBook(Author entity){
        entityManager.merge(entity);
    }

    public void removeBook(Author entity){
        entityManager.remove(
                entityManager.contains(entity) ? entity : entityManager.merge(entity));
    }

    public Author read(long id){

        return entityManager.find(Author.class, id);
    }

    public List<Author> readAll() {
        Query q = this.entityManager
                .createQuery("SELECT e FROM Author e");
        return q.getResultList();
    }

}