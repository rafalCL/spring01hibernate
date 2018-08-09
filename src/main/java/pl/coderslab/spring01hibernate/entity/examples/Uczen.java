package pl.coderslab.spring01hibernate.entity.examples;

import javax.persistence.*;
import java.util.List;

@Entity
public class Uczen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @ManyToMany(mappedBy = "uczniowie", fetch = FetchType.EAGER)
    List<Nauczyciel> nauczyciele;

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

    public List<Nauczyciel> getNauczyciele() {
        return nauczyciele;
    }

    public void setNauczyciele(List<Nauczyciel> nauczyciele) {
        this.nauczyciele = nauczyciele;
    }

    @Override
    public String toString() {
        return "Uczen{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
