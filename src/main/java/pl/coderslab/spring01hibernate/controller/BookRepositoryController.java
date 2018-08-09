package pl.coderslab.spring01hibernate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.spring01hibernate.entity.Book;
import pl.coderslab.spring01hibernate.entity.Category;
import pl.coderslab.spring01hibernate.repository.BookRepository;
import pl.coderslab.spring01hibernate.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/book/repo")
public class BookRepositoryController {
    @Autowired
    private BookRepository br;
    @Autowired
    private CategoryRepository cr;

    @GetMapping("/add/{title}/{author}/{rating}")
    @ResponseBody
    public String add(@PathVariable String title,
                      @PathVariable String author,
                      @PathVariable int rating){
        Book b = new Book();
        b.setTitle(title);
//        b.setAuthor(author);
        b.setRating(rating);

        this.br.save(b);

        return "Dodano, id=" + b.getId();
    }

    @GetMapping("/list")
    public String list(){
        return "book/list";
    }

    @GetMapping("/list/title/{title}")
    public String listByTitle(@PathVariable String title, Model m){
        List<Book> books = this.br.findAllByTitle(title);
        m.addAttribute("books", books);

        return "book/list";
    }

    @GetMapping("/list/category/{catName}")
    public String listByCategory(@PathVariable String catName, Model m){
        Category category = this.cr.findOneByName(catName);
        List<Book> books = this.br.findAllByCategory(category);

// TODO alternative List<Book> books2 = this.br.findAllByCategoryName(catName);
        m.addAttribute("books", books);

        return "book/list";
    }

    @GetMapping("/list/catid/{catId}")
    public String listByCategory(@PathVariable long catId, Model m){
        List<Book> books = this.br.findAllByCategoryId(catId);
        m.addAttribute("books", books);

        return "book/list";
    }

    @GetMapping("/list/jpqltest")
    public String listJpqlTest(Model m){
        // 1
        List<Book> books = this.br.findAllByJpqlTitle("potop");
        //2
        Category category = this.cr.findOneByName("poezja");
        List<Book> books2 = this.br.findAllByJpqlCategory(category);
        //3
        List<Book> books3 = this.br.findAllWhereTitleContains("propozycja");
        List<Book> allBooks = new ArrayList<>();

        allBooks.addAll(books);
        allBooks.addAll(books2);
        allBooks.addAll(books3);

        m.addAttribute("books", allBooks);

        return "book/list";
    }

    @GetMapping("/list/jpqltest2")
    public String listJpqlTest2(Model m){
        Category category = this.cr.findOneByName("proza");
        Book book1 = this.br.findFirstByCategoryOrderByTitle(category);
        Book book2 = this.br.findFirstByCategoryOrderByTitleDesc(category);
        List<Book> allBooks = new ArrayList<>();

        allBooks.add(book1);
        allBooks.add(book2);

        m.addAttribute("books", allBooks);

        return "book/list";
    }

    @GetMapping("/resetrating/{newRating}")
    public String resetRating(@PathVariable int newRating){
        this.br.resetRating(newRating);

        return "book/list";
    }

    @ModelAttribute("books")
    public List<Book> books(){
        return this.br.findAll();
    }
}
