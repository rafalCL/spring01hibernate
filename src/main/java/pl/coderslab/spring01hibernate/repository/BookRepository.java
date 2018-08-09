package pl.coderslab.spring01hibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.coderslab.spring01hibernate.entity.Book;
import pl.coderslab.spring01hibernate.entity.Category;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long>,
                                        ResetRatingInterface {
    List<Book> findAllByTitle(String title);
    List<Book> findAllByCategory(Category category);
    List<Book> findAllByCategoryId(long id);
    // using JPQL
    @Query("SELECT b FROM Book b WHERE b.title = ?1") // with parameter index
    List<Book> findAllByJpqlTitle(String title);
    @Query("SELECT b FROM Book b WHERE b.category = :categ")
    List<Book> findAllByJpqlCategory(@Param("categ") Category cat);
    @Query("SELECT b FROM Book b WHERE b.title LIKE %:part%")
    List<Book> findAllWhereTitleContains(@Param("part") String part);

    Book findFirstByCategoryOrderByTitle(Category c);
    Book findFirstByCategoryOrderByTitleDesc(Category c);
}
