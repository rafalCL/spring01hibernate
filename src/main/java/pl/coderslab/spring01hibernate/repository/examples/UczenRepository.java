package pl.coderslab.spring01hibernate.repository.examples;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.spring01hibernate.entity.examples.Uczen;

public interface UczenRepository
        extends JpaRepository<Uczen, Long> {
}
