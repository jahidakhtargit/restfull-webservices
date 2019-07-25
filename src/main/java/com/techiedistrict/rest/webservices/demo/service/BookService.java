package com.techiedistrict.rest.webservices.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techiedistrict.rest.webservices.demo.dao.BookDao;
import com.techiedistrict.rest.webservices.demo.model.Book;

@Service
public class BookService {
	
	@Autowired
	private BookDao bookDao;
	
	public List<Book> retrieveAllBooks() {
		return bookDao.getAllBooks();
	}
	
	public Book retrieveBookByIsbn(String isbn) {
		return bookDao.findBookByIsbn(isbn);
	}
	
	public List<Book> retrieveAllBooksByAuthor(String author) {
		return bookDao.findBooksByAuthor(author);
	}
	
	public Book createBook(Book book) {
		return bookDao.saveBook(book);
	}

}
