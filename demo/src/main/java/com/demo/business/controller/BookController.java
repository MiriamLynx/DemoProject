package com.demo.business.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.persistence.BookRepository;

@Controller
public class BookController {

    @Autowired
    private BookRepository repository;
    
    @RequestMapping(path = "/home", produces = "application/json")
    String findAll(final Model model) {
    	model.addAttribute("books", repository.findAll());
    	return "home";
    }

}
