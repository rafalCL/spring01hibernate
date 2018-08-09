package pl.coderslab.spring01hibernate.repository.examples;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.spring01hibernate.entity.examples.Klasa;

public interface KlasaRepository
        extends JpaRepository<Klasa, Long> {
}
