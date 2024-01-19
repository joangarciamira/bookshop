package com.polarbookshop.catalogservice.web;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.polarbookshop.catalogservice.domain.BookNotFoundException;
import com.polarbookshop.catalogservice.domain.BookService;

@WebMvcTest(controllers = BookController.class)
public class BookControllerMvcTests {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private BookService bookService;
	
	@Test
	void whenGetBookNotExistingThenShouldReturn404() throws Exception {
		String isbn = "12736831374";
		BDDMockito.given(bookService.viewBookDetails(isbn))
			.willThrow(BookNotFoundException.class);
		
		mockMvc
			.perform(MockMvcRequestBuilders.get("/books/" +isbn))
			.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
			
}
