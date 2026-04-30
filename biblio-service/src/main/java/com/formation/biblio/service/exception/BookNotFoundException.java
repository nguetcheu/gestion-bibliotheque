package com.formation.biblio.service.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(Long id) { super("Livre introuvable : id=" + id); }
    public BookNotFoundException(String isbn) { super("Livre introuvable : isbn=" + isbn); }
}
