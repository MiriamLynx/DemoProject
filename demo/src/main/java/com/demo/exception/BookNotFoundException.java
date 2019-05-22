package com.demo.exception;

public class BookNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5186837203169974298L;

	public BookNotFoundException(Long id) {
        super("Book id not found : " + id);
    }

}
