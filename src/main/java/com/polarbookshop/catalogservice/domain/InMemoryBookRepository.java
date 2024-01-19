package com.polarbookshop.catalogservice.domain;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

@Repository
public class InMemoryBookRepository implements BookRepository {
	
	private static final Map<String, Book> books = 
			new ConcurrentHashMap<>();

	@Override
	public Iterable<Book> findAll() {
		// TODO Auto-generated method stub
		return books.values();
	}

	@Override
	public Optional<Book> findByIsbn(String isbn) {
		// TODO Auto-generated method stub
		return existsByIsbn(isbn) ? Optional.of(books.get(isbn)) : 
			Optional.empty();
	}

	@Override
	public boolean existsByIsbn(String isbn) {
		// TODO Auto-generated method stub
		return books.get(isbn) != null;
	}

	@Override
	public Book save(Book book) {
		// TODO Auto-generated method stub
		books.put(book.isbn(), book);
		return book;
	}

	@Override
	public void deleteByIsbn(String isbn) {
		// TODO Auto-generated method stub
		books.remove(isbn);
	}

}
