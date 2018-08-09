package pl.coderslab.spring01hibernate.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Transactional
public class BookRepositoryImpl implements ResetRatingInterface {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void resetRating(int rating) {
        final String sql = "UPDATE books SET rating = ?1";
        Query q = this.entityManager.createNativeQuery(sql);
        q.setParameter(1, rating);
        q.executeUpdate();
    }
}
