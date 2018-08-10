package pl.coderslab.spring01hibernate.repository.security;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.spring01hibernate.entity.security.User;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    void delete(User user);
}