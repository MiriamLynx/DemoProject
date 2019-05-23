package com.demo.business.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.exception.BookNotFoundException;
import com.demo.model.Book;
import com.demo.persistence.BookRepository;

@RestController
public class BookRestController {

    @Autowired
    private BookRepository repository;
    
    @GetMapping(path = "/", produces = "application/json")
    List<Book> findAll() {
        return repository.findAll();
    }

    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    Book newBook(@RequestBody Book newBook) {
        return repository.save(newBook);
    }

    @GetMapping(path = "/", params = "id", produces = "application/json")
    Book findOne(@RequestParam Long id) {
        return repository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
    }

    @PutMapping(path = "/", params = "id", consumes = "application/json", produces = "application/json")
    Book update(@RequestBody Book updatedBook, @RequestParam Long id) {

        return repository.findById(id)
                .map(x -> {
                    x.setName(updatedBook.getName());
                    x.setAuthor(updatedBook.getAuthor());
                    x.setPrice(updatedBook.getPrice());
                    return repository.save(x);
                })
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    @DeleteMapping(path = "/", params = "id")
    void deleteBook(@RequestParam Long id) {
        repository.deleteById(id);
    }
    
    @GetMapping(path = "/last", produces = "application/json")
    Book findLast() {
        return repository.findTopByOrderByIdDesc();
    }

}
