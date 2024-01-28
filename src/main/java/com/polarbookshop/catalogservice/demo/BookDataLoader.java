package com.polarbookshop.catalogservice.demo;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.domain.BookRepository;

@Component
@Profile(value = "testdata")
public class BookDataLoader {

	private final BookRepository bookRepository;

	public BookDataLoader(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void loadBookTestData() {
		Book book1 = new Book("1234567890130", "Asterix legionario", "Goscinny y Uderzo", 10.00);
		Book book2 = new Book("1234567890132", "Asterix y el caldero", "Goscinny y Uderzo", 10.00);
		bookRepository.save(book1);
		bookRepository.save(book2);
	}
}
