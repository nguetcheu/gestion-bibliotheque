package com.formation.biblio.service.exception;

public class IsbnAlreadyExistsException extends RuntimeException {
    public IsbnAlreadyExistsException(String isbn) { super("ISBN déjà existant : " + isbn); }
}
