package com.techiedistrict.rest.webservices.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.techiedistrict.rest.webservices.demo.exception.BookNotFoundException;
import com.techiedistrict.rest.webservices.demo.model.Book;
import com.techiedistrict.rest.webservices.demo.model.BookResource;
import com.techiedistrict.rest.webservices.demo.service.BookService;

@RestController
@RequestMapping(value = "/books", produces = "application/hal+json")
public class BookController {

	@Autowired                
	private BookService bookService;

	
	@GetMapping(path = "all")
	public ResponseEntity<Resources<BookResource>> retrieveAllBooks() {
		final List<BookResource> collection =
				bookService.retrieveAllBooks().stream().map(BookResource::new).collect(Collectors.toList());
		final Resources<BookResource> resources = new Resources<>(collection);
		final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
		resources.add(new Link(uriString, "self"));
		return ResponseEntity.ok(resources);
	}

	@GetMapping(path = "/author/{author}")
	public ResponseEntity<Resources<BookResource>> retrieveAllBooksByAuthor(@PathVariable String author) {
		final List<BookResource> collection = bookService.retrieveAllBooksByAuthor(author).stream().
				map(BookResource::new).collect(Collectors.toList());
		final Resources<BookResource> resources = new Resources<>(collection);
		final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
		resources.add(new Link(uriString, "self"));
		return ResponseEntity.ok(resources);
	}

	@GetMapping(path = "/isbn/{isbn}")
	public Resource<Book> retrieveBookByIsbn(@PathVariable String isbn) {
		Book book = bookService.retrieveBookByIsbn(isbn);
		if(book == null) {
			throw new BookNotFoundException("Book with isbn "+isbn +" is not found");
		}

		Resource<Book> result = new Resource<>(book);
		ControllerLinkBuilder linkTo = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder
						.methodOn(this.getClass()).retrieveAllBooksByAuthor(book.getAuthor()));
		result.add(linkTo.withRel("Books by author "+book.getAuthor()));

		return result;
	}
	
	@PostMapping()
	public ResponseEntity<BookResource> createBook(@Valid @RequestBody Book book) {
		Book createdBook = bookService.createBook(book);
		BookResource bookResource = new BookResource(createdBook);	
		//return ResponseEntity.ok(bookResource);
		return new ResponseEntity<>(bookResource, HttpStatus.CREATED);
		
	}

}
