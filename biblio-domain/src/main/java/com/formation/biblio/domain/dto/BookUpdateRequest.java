package com.formation.biblio.domain.dto;

import jakarta.validation.constraints.*;
public record BookUpdateRequest(
        @NotBlank @Size(max = 300) String title,
        @NotBlank @Size(max = 200) String authorName,
        @NotNull @Min(0) @Max(2100) Integer year
) {}
