package pl.coderslab.spring01hibernate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.spring01hibernate.entity.Book;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Controller
@RequestMapping("/validation")
public class ValidationController {
    @Autowired
    private Validator validator;

    @GetMapping("/validate")
    @ResponseBody
    public String validate(){
        Book b = new Book();
        b.setPages(42);
        Set<ConstraintViolation<Book>> violations
                = validator.validate(b);

        if (violations.isEmpty()){
            return "Obiekt poprawny! Przeszedł walidację.";
        } else {
            String resultTxt = "Naruszenia wymagań: <br>\r\n";
            for (ConstraintViolation<Book> v : violations){
                resultTxt += v.getPropertyPath()
                        + " : " + v.getMessage() + "<br>\r\n";
            }

            return resultTxt;
        }
    }
}
