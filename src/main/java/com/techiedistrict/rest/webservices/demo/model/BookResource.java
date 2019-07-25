package com.techiedistrict.rest.webservices.demo.model;

import org.springframework.hateoas.ResourceSupport;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import lombok.Getter;
import com.techiedistrict.rest.webservices.demo.controller.BookController;


@Getter
public class BookResource extends ResourceSupport {

	private final Book book;

	public BookResource(final Book book) {
		this.book = book;
		String isbn = book.getIsbn();
		add(linkTo(methodOn(BookController.class).retrieveBookByIsbn(isbn)).withSelfRel());
	}

}
