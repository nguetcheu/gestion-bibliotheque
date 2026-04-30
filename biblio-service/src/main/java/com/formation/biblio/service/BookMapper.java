package com.formation.biblio.service;

import com.formation.biblio.domain.Book;
import com.formation.biblio.domain.dto.BookResponse;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    public BookResponse toResponse(Book book) {
        return new BookResponse(
                book.getId(), book.getIsbn(), book.getTitle(),
                book.getAuthor().getName(), book.getYear()
        );
    }
}
