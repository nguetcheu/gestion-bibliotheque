package com.formation.biblio.domain.dto;

public record BookResponse(
        Long id, String isbn, String title, String authorName, Integer year
) {}
