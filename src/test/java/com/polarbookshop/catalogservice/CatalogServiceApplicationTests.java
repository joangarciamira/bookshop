package com.polarbookshop.catalogservice;

import com.polarbookshop.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {"spring.cloud.config.enabled=false", "spring.profiles.active=integration"})
//@SpringBootTest
@ActiveProfiles("integration")
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CatalogServiceApplicationTests {

	@Autowired
	private WebTestClient webTestClient;
	
 	@Test
 	void contextLoads() {
 	}
	
	@Test
	void whenPostRequestThenBookCreated() {
		Book expectedBook = Book.of("1234567890125", "Asterix y los godos", "Goscinny y Uderzo", 10.00, "Albert-René");
		
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
