package com.polarbookshop.catalogservice.domain;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void whenBookToCreateAlreadyExistsThenThrows() {
        var bookIsbn = "1234561232";
        var bookToCreate = Book.of(bookIsbn, "La vuelta a la Galia", "Goscinny y Uderzo", 10.00, "Albert-René");
        when(bookRepository.existsByIsbn(bookIsbn)).thenReturn(true);
        assertThatThrownBy(() -> bookService.addBookToCatalog(bookToCreate))
                .isInstanceOf(BookAlreadyExistsException.class)
                .hasMessage("The book with isbn " + bookIsbn + " already exists.");
    }

	@Test
	void whenBookToReadDoesNotExistThenThrows() {
		var bookIsbn = "1234561232";
		when(bookRepository.findByIsbn(bookIsbn)).thenReturn(Optional.empty());
		assertThatThrownBy(() -> bookService.viewBookDetails(bookIsbn))
				.isInstanceOf(BookNotFoundException.class)
				.hasMessage("The book with isbn " + bookIsbn + " was not found.");
	}

}