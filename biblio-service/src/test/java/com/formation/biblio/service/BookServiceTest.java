package com.formation.biblio.service;

import com.formation.biblio.domain.dto.BookCreateRequest;
import com.formation.biblio.service.exception.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {
    @Mock BookRepository bookRepo;
    @Mock AuthorRepository authorRepo;
    @Mock BookMapper mapper;
    @InjectMocks BookService service;

    @Test
    void create_isbnDejaExistant_leveException() {
        when(bookRepo.existsByIsbn("X")).thenReturn(true);
        assertThatThrownBy(() -> service.create(
                new BookCreateRequest("X", "T", "A", 2020)))
                .isInstanceOf(IsbnAlreadyExistsException.class);
        verify(bookRepo, never()).save(any());
    }

    @Test
    void findById_inconnu_leveException() {
        when(bookRepo.findById(99L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.findById(99L))
                .isInstanceOf(BookNotFoundException.class);
    }
}
