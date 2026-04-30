package com.formation.biblio.domain;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String isbn;

    @Column(nullable = false, length = 300)
    private String title;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private Author author;

    @Column(name = "publication_year", nullable = false)
    private Integer year;

    protected Book() {}

    public Book(String isbn, String title, Author author, Integer year) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public Long getId() { return id; }
    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public Author getAuthor() { return author; }
    public Integer getYear() { return year; }
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(Author author) { this.author = author; }
    public void setYear(Integer year) { this.year = year; }

    @Override
    public boolean equals(Object o) {
        return o instanceof Book b && Objects.equals(id, b.id);
    }
    @Override public int hashCode() { return Objects.hashCode(id); }
}