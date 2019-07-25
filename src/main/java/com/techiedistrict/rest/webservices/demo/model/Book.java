package com.techiedistrict.rest.webservices.demo.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.hateoas.ResourceSupport;

@ApiModel(description = "Description about the Book")
public class Book extends ResourceSupport {
	Integer bookId;
	
	@ApiModelProperty(notes = "ISBN-10, ISBN should be 10 characters")
	@Size(min = 10, max=10)
	String isbn;
	
	@ApiModelProperty(notes = "Title of the book. It should be minimun 2 characters.")
	@Size(min = 2)
	String title;
	
	@ApiModelProperty(notes = "Author should be minimun 5 characters.")
	@Size(min = 2)
	String author;
	
	String genre;
	
	@ApiModelProperty(notes = "Price is in INR, it should be >= 10")
	@Min(10)
	Integer priceInINR;
	
	public Book() {
		
	}

	public Book(String isbn, String title, String author, String genre, Integer priceInINR) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.priceInINR = priceInINR;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Integer getPriceInINR() {
		return priceInINR;
	}

	public void setPriceInINR(int priceInINR) {
		this.priceInINR = priceInINR;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}	
	
}
