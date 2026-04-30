package com.formation.biblio.service;

import com.formation.biblio.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class BookRepositoryTest {
    @Autowired BookRepository bookRepo;
    @Autowired TestEntityManager em;

    @Test
    void findByIsbn_existant_renvoieLeLivre() {
        Author bloch = em.persist(new Author("Joshua Bloch"));
        em.persist(new Book("978-1", "Effective Java", bloch, 2018));
        em.flush();
        assertThat(bookRepo.findByIsbn("978-1")).isPresent();
    }

    @Test
    void findByIsbn_inconnu_renvoieEmpty() {
        assertThat(bookRepo.findByIsbn("inconnu")).isEmpty();
    }

    @Test
    void existsByIsbn() {
        Author a = em.persist(new Author("A"));
        em.persist(new Book("X", "T", a, 2020));
        em.flush();
        assertThat(bookRepo.existsByIsbn("X")).isTrue();
        assertThat(bookRepo.existsByIsbn("Y")).isFalse();
    }
}