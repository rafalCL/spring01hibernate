package pl.coderslab.spring01hibernate.entity.examples;

import pl.coderslab.spring01hibernate.repository.examples.UczenRepository;

import javax.persistence.*;
import java.util.List;

@Entity
public class Nauczyciel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Klasa klasaWychowawcza;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Uczen> uczniowie;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Klasa getKlasaWychowawcza() {
        return klasaWychowawcza;
    }

    public void setKlasaWychowawcza(Klasa klasaWychowawcza) {
        this.klasaWychowawcza = klasaWychowawcza;
    }

    public List<Uczen> getUczniowie() {
        return uczniowie;
    }

    public void setUczniowie(List<Uczen> uczniowie) {
        this.uczniowie = uczniowie;
    }

    @Override
    public String toString() {
        return "Nauczyciel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", klasaWychowawcza=" + klasaWychowawcza +
                '}';
    }
}
