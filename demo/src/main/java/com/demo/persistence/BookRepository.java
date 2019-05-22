package com.demo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	
	Book findTopByOrderByIdDesc();
}
