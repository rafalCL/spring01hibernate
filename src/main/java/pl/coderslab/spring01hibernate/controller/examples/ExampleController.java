package pl.coderslab.spring01hibernate.controller.examples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.spring01hibernate.entity.examples.Klasa;
import pl.coderslab.spring01hibernate.entity.examples.Nauczyciel;
import pl.coderslab.spring01hibernate.entity.examples.Uczen;
import pl.coderslab.spring01hibernate.repository.examples.NauczycielRepository;
import pl.coderslab.spring01hibernate.repository.examples.UczenRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/example")
public class ExampleController {
    @Autowired
    private NauczycielRepository nauczycielRepository;
    @Autowired
    private UczenRepository uczenRepository;

    @GetMapping("/in")
    @ResponseBody
    public String insertNauczyciel(){
        Nauczyciel n = new Nauczyciel();
        n.setName("pierwszy");
        this.nauczycielRepository.save(n);

        return "dodano";
    }

    @GetMapping("/inwk")
    @ResponseBody
    public String insertNauczycielWithKlasa(){
        Nauczyciel n = new Nauczyciel();
        n.setName("drugi with klasa");

        Klasa k = new Klasa();
        k.setName("drugiego nauczyciela");

        n.setKlasaWychowawcza(k);

        this.nauczycielRepository.save(n);

        return "dodano";
    }

    @GetMapping("/updatecascaded")
    @ResponseBody
    public String updatecascaded(){
        Nauczyciel n = nauczycielRepository.findOne(3L);
        n.getKlasaWychowawcza().setName("nowa nazwa klasy");

        this.nauczycielRepository.save(n);

        return "dodano";
    }

    @GetMapping("/adduczniowie")
    @ResponseBody
    public String adduczniowie(){
        Uczen u1 = new Uczen();
        u1.setName("alan");
        Uczen u2 = new Uczen();
        u1.setName("ola");
        Uczen u3 = new Uczen();
        u1.setName("robert");
        List<Uczen> uczens = new ArrayList<>();
        uczens.add(u1);
        uczens.add(u2);
        uczens.add(u3);

        uczenRepository.save(uczens);

        return "dodano";
    }

    @GetMapping("/adduczniowietonauczyciel")
    @ResponseBody
    public String adduczniowietonauczyciel(){
        Nauczyciel n = nauczycielRepository.findOne(3L);
        n.getUczniowie().add(uczenRepository.findOne(1L));
        n.getUczniowie().add(uczenRepository.findOne(2L));
        n.getUczniowie().add(uczenRepository.findOne(3L));

        nauczycielRepository.save(n);

        return "dodano";
    }

    @GetMapping("/getnauczycielwithuczniowie")
    @ResponseBody
    public String getnauczycielwithuczniowie(){
        Nauczyciel n = nauczycielRepository.findOne(3L);

        return n.getUczniowie().toString();
    }

    @GetMapping("/getuczenwithnauczyciele")
    @ResponseBody
    public String getuczenwithnauczyciele(){
        Uczen u = uczenRepository.findOne(1L);

        return u.getNauczyciele().toString();
    }
}
