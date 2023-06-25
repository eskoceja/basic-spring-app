package com.library.system.crud.controller;

import com.library.system.crud.model.Author;
import com.library.system.crud.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    //Read
    @GetMapping
    public String getAllAuthors(Model model) {
        model.addAttribute("authors", authorRepository.findAll());
        return "author-list";
    }

    @GetMapping("/{id}")
    public String getAuthorById(@PathVariable("id") Long id, Model model) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid author Id: " + id));
        model.addAttribute("author", author);
        return "author-details";
    }

    //Create
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("author", new Author());
        return "author-create";
    }

    @PostMapping
    public String createAuthor(@ModelAttribute("author") Author author) {
        authorRepository.save(author);
        return "redirect:/authors";
    }

    //Update
    @GetMapping("/{id}/edit")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid author Id: " + id));
        model.addAttribute("author", author);
        return "author-update";
    }

    @PostMapping("/{id}")
    public String updateAuthor(@PathVariable("id") Long id, @ModelAttribute("author") Author updatedAuthor) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid author Id: " + id));
        author.setName(updatedAuthor.getName());
        author.setBooks(updatedAuthor.getBooks());
        authorRepository.save(author);
        return "redirect:/authors";
    }

    //Delete
    @GetMapping("/{id}/delete")
    public String deleteAuthor(@PathVariable("id") Long id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid author Id: " + id));
        authorRepository.delete(author);
        return "redirect:/authors";
    }

}
