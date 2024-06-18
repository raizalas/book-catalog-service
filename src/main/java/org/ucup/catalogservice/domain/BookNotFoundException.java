package org.ucup.catalogservice.domain;

public class BookNotFoundException extends RuntimeException{

    public BookNotFoundException(String Isbn) {
        super("The book with the ISBN " + Isbn + " was not found.");
    }

}
