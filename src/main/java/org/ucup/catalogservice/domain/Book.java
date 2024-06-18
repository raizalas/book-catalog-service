package org.ucup.catalogservice.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record Book(
    @NotBlank(message = "ISBN must not be empty.")
    @Pattern(
        regexp = "^([0-9]{10}|[0-9]{13})",
        message = "Not a valid ISBN-10 or ISBN-13 format"
    )
    String isbn,

    @NotBlank(message = "The book title must be defined.")
    String title,

    @NotBlank(message = "The book author must be defined.")
    String author,

    @NotNull(message = "The price must be defined.")
    @Positive(message = "The price must be greater than zero")
    Double price
) {

}
