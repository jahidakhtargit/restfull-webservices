package com.techiedistrict.rest.webservices.demo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.techiedistrict.rest.webservices.demo.model.Book;

@Repository
public class BookDao {
	
	private static List<Book> books = new ArrayList<>();
	private static int bookCounter = 11;
	
	static {
		Book book1 = new Book("1478948418", "The Other Side of Midnight", "Sidney Sheldon", "Suspense", 250);
		book1.setBookId(1);
		books.add(book1);
		
		Book book2 = new Book("0330290959", "If Tomorrow Comes", "Sidney Sheldon", "Suspense", 150);
		book2.setBookId(2);
		books.add(book2);
		
		Book book3 = new Book("0451125908", "The Second Lady", "Irving Wallace", "Thriller", 200);
		book3.setBookId(3);
		books.add(book3);
		
		Book book4 = new Book("0060935464", "To Kill a Mockingbird", "Harper Lee", "Classic", 200);
		book4.setBookId(4);
		books.add(book4);
		
		Book book5 = new Book("014028334X", "One Flew over the Cuckoo's Nest", "Ken Kesey", "Classic", 250);
		book5.setBookId(5);
		books.add(book5);
		
		Book book6 = new Book("1400079985", "War and Peace", " Leo Tolstoy", "Classic", 300);
		book6.setBookId(6);
		books.add(book6);
		
		Book book7 = new Book("0440224675", "Hannibal", "Thomas Harris", "Thriller", 400);
		book7.setBookId(7);
		books.add(book7);
		
		Book book8 = new Book("0312924585", "The Silence of the Lambs", "Thomas Harris", "Thriller", 350);
		book8.setBookId(8);
		books.add(book8);
		
		Book book9 = new Book("B000089A07", "Red Dragon", "Thomas Harris", "Thriller", 250);
		book9.setBookId(9);
		books.add(book9);
		
		Book book10 = new Book("070993007508", "The Notebook", "Nicholas Sparks", "Romance", 300);
		book10.setBookId(10);
		books.add(book10);
		
		Book book11 = new Book("0446608955", "A Walk to Remember", "Nicholas Sparks", "Romance", 200);
		book11.setBookId(11);
		books.add(book11);
		
	}
	
	public List<Book> getAllBooks() {
		return books;
	}
	
	public Book findBookByIsbn(String isbn) {
		Optional<Book> book = books.stream().filter(b -> b.getIsbn().equalsIgnoreCase(isbn)).findFirst();
		if(book.isEmpty()) {
			return null;
		} else {
			return book.get();
		}
	}
	
	public List<Book> findBooksByAuthor(String author) {
		List<Book> books = BookDao.books.stream().
				filter(b -> b.getAuthor().equalsIgnoreCase(author)).
				collect(Collectors.toList());
		
		return books;
	}
	
	
	public Book saveBook(Book book) {
		if(book.getBookId() == null) {
			bookCounter++;
			book.setBookId(bookCounter);
		}
		books.add(book);
		return book;
	}

}
