package pl.coderslab.spring01hibernate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.spring01hibernate.dao.AuthorDao;
import pl.coderslab.spring01hibernate.dao.BookDao;
import pl.coderslab.spring01hibernate.dao.PublisherDao;
import pl.coderslab.spring01hibernate.entity.Author;
import pl.coderslab.spring01hibernate.entity.Book;
import pl.coderslab.spring01hibernate.entity.Publisher;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookDao bookDao;
    @Autowired
    private PublisherDao publisherDao;
    @Autowired
    private AuthorDao authorDao;

    @GetMapping("/add/{title}/{author}/{rating}")
    @ResponseBody
    public String add(@PathVariable String title,
                      @PathVariable String author,
                      @PathVariable int rating){
        Book b = new Book();
        b.setTitle(title);
//        b.setAuthor(author);
        b.setRating(rating);

        this.bookDao.create(b);

        return "Dodano, id=" + b.getId();
    }

    @GetMapping("/addform")
    public String addBookForm(Model model){
        model.addAttribute("book", new Book());

        return "book/addForm";
    }

    @PostMapping("/addform")
    public String addBookFormPost(@ModelAttribute Book book){
        this.bookDao.create(book);

        return "ok";
    }

    @ModelAttribute("publishers")
    public List<Publisher> publishers(){
        return this.publisherDao.readAll();
    }

    @ModelAttribute("authors")
    public List<Author> authors(){
        return this.authorDao.readAll();
    }
}