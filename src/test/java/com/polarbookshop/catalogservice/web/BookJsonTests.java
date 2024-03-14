package com.polarbookshop.catalogservice.web;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.time.Instant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import com.polarbookshop.catalogservice.domain.Book;

@JsonTest
public class BookJsonTests {

	@Autowired
	private JacksonTester<Book> json;
	
	@Test
	void testSerialize() throws IOException {
		Book book = Book.of("1234567890126", "Asterix gladiador", "Goscinny y Uderzo", 10.00);
		JsonContent<Book> jsonContent = json.write(book);
		assertThat(jsonContent).extractingJsonPathStringValue("@.isbn")
			.isEqualTo(book.isbn());
	}
	
	@Test
	void testDeserialize() throws IOException {
		Instant instant = Instant.parse("2024-02-13T21:13:37.135029Z");
		String jsonContent = """
				{
				    "id": 0,
					"isbn": "1234567890129",
					"title": "Asterix y Cleopatra",
					"author": "Goscinny y Uderzo",
					"price": 10.00,
					"createdDate": "2024-02-13T21:13:37.135029Z",
					"lastModifiedDate": "2024-02-13T21:13:37.135029Z",
					"version": 0
				}
				""";
		Book book = json.parse(jsonContent).getObject();
		
		assertThat(book).usingRecursiveComparison()
			.isEqualTo(new Book(0L, "1234567890129", "Asterix y Cleopatra", "Goscinny y Uderzo", 10.00, instant, instant, 0));
	}
}
