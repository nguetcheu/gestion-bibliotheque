package com.formation.biblio.service;

import com.formation.biblio.domain.Author;
import com.formation.biblio.domain.Book;
import com.formation.biblio.domain.dto.*;
import com.formation.biblio.service.exception.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BookService {
    private final BookRepository bookRepo;
    private final AuthorRepository authorRepo;
    private final BookMapper mapper;

    public BookService(BookRepository bookRepo, AuthorRepository authorRepo, BookMapper mapper) {
        this.bookRepo = bookRepo;
        this.authorRepo = authorRepo;
        this.mapper = mapper;
    }

    public BookResponse create(BookCreateRequest req) {
        if (bookRepo.existsByIsbn(req.isbn()))
            throw new IsbnAlreadyExistsException(req.isbn());
        Author author = authorRepo.findByName(req.authorName())
                .orElseGet(() -> authorRepo.save(new Author(req.authorName())));
        return mapper.toResponse(bookRepo.save(new Book(req.isbn(), req.title(), author, req.year())));
    }

    @Transactional(readOnly = true)
    public BookResponse findById(Long id) {
        return mapper.toResponse(bookRepo.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id)));
    }

    @Transactional(readOnly = true)
    public Page<BookResponse> findAll(Pageable pageable) {
        return bookRepo.findAll(pageable).map(mapper::toResponse);
    }

    public BookResponse update(Long id, BookUpdateRequest req) {
        Book book = bookRepo.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        Author author = authorRepo.findByName(req.authorName())
                .orElseGet(() -> authorRepo.save(new Author(req.authorName())));
        book.setTitle(req.title());
        book.setAuthor(author);
        book.setYear(req.year());
        return mapper.toResponse(book);
    }

    public void delete(Long id) {
        if (!bookRepo.existsById(id)) throw new BookNotFoundException(id);
        bookRepo.deleteById(id);
    }
}
