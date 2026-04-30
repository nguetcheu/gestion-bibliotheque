package com.formation.biblio.domain;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 200)
    private String name;

    protected Author() {}

    public Author(String name) {
        this.name = Objects.requireNonNull(name);
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override
    public boolean equals(Object o) {
        return o instanceof Author a && Objects.equals(id, a.id);
    }
    @Override public int hashCode() { return Objects.hashCode(id); }
}