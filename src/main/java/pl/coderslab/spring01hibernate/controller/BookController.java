package pl.coderslab.spring01hibernate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.spring01hibernate.dao.BookDao;
import pl.coderslab.spring01hibernate.dao.PublisherDao;
import pl.coderslab.spring01hibernate.entity.Book;
import pl.coderslab.spring01hibernate.entity.Publisher;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookDao bookDao;

    @GetMapping("/add/{title}/{author}/{rating}")
    @ResponseBody
    public String add(@PathVariable String title,
                      @PathVariable String author,
                      @PathVariable int rating){
        Book b = new Book();
        b.setTitle(title);
        b.setAuthor(author);
        b.setRating(rating);

        this.bookDao.create(b);

        return "Dodano, id=" + b.getId();
    }
}
