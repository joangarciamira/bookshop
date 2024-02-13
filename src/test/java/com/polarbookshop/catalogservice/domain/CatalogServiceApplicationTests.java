package com.polarbookshop.catalogservice.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CatalogServiceApplicationTests {

	@Autowired
	private WebTestClient webTestClient;
	
	@Test
	void whenPostRequestThenBookCreated() {
		Book expectedBook = Book.of("1234567890125", "Asterix y los godos", "Goscinny y Uderzo", 10.00);
		
		webTestClient.post()
				.uri("/books")
				.bodyValue(expectedBook)
				.exchange()
				.expectStatus().isCreated()
				.expectBody(Book.class)
				.value(actualBook -> {
					assertThat(actualBook).isNotNull();
					assertThat(actualBook.isbn()).isEqualTo(expectedBook.isbn());
				});
				
	}
}
