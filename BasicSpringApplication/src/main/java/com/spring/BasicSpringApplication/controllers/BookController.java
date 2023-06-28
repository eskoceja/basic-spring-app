package com.spring.BasicSpringApplication.controllers;

import com.spring.BasicSpringApplication.models.Book;
import com.spring.BasicSpringApplication.repositories.AuthorRepository;
import com.spring.BasicSpringApplication.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    //CREATE
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorRepository.findAll());
        return "create-book.html";
    }

    @PostMapping
    public String createBook(@ModelAttribute Book newBook) {
        bookRepository.save(newBook);
        return "redirect:/books";
    }

    //READ
    @GetMapping
    public String getAllBooks(Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", bookRepository.findAll());
        return "book-list.html";
    }

    @GetMapping("/{id}")
    public String getBookById(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "book-list.html";
    }


    //UPDATE
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Book book = bookRepository.findById(id).orElse(null);
        if(book == null) {
            return "error.html";
        }
        model.addAttribute("book", book);
        model.addAttribute("author", authorRepository.findAll());
        return "update-book.html";
    }

    @PostMapping("/{id}")
    public String updateBook(@PathVariable("id") Long id, @ModelAttribute Book updatedBook) {
        Book book = bookRepository.findById(id).orElse(null);
        if(book == null) {
            return "error.html";
        }
        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());

        bookRepository.save(book);
        return "redirect:/books/{id}";
    }

    //DELETE
    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book == null) {
            return "error.html";
        }
        bookRepository.delete(book);
        return "redirect:/books";
    }
}
