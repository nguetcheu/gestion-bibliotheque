package com.formation.biblio.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class BookTest {
    @Test
    void creation_renseigne_les_champs() {
        Author author = new Author("Joshua Bloch");
        Book book = new Book("978-1", "Effective Java", author, 2018);
        assertThat(book.getIsbn()).isEqualTo("978-1");
        assertThat(book.getTitle()).isEqualTo("Effective Java");
        assertThat(book.getAuthor().getName()).isEqualTo("Joshua Bloch");
        assertThat(book.getYear()).isEqualTo(2018);
    }
}