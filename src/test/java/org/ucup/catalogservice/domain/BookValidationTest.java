package org.ucup.catalogservice.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class BookValidationTest {
    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("All fields are correct for Book entity.")
    void whenAllFieldsAreCorrect() {
        Book book = new Book("1234567890123", "Title", "Author", 10d);
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).isEmpty();
    }

    @Test
    @DisplayName("ISBN Incorrectly Formatted.")
    void whenIsbnIncorrectlyFormatted() {
        Book book = new Book("12345678", "title", "author", 10d);
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("Not a valid ISBN-10 or ISBN-13 format");
    }

    @Test
    @DisplayName("Title is empty.")
    void whenTitleIsBlank() {
        Book book = new Book("1234567890", "", "author", 10d);
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations)
                .extracting(ConstraintViolation::getMessage)
                .containsExactlyInAnyOrder("The book title must be defined.");
    }
}
