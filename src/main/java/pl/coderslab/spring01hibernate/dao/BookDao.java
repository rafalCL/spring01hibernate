package pl.coderslab.spring01hibernate.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.spring01hibernate.entity.Author;
import pl.coderslab.spring01hibernate.entity.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class BookDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(Book entity){
        entityManager.persist(entity);
    }

    public void editBook(Book entity){
        entityManager.merge(entity);
    }

    public void removeBook(Book entity){
        entityManager.remove(
                entityManager.contains(entity) ? entity : entityManager.merge(entity));
    }

    public Book read(long id){
        return entityManager.find(Book.class, id);
    }

    public List<Book> readAll() {
        Query q = this.entityManager
                .createQuery("SELECT e FROM Book e WHERE proposition=false");
        return q.getResultList();
    }

}