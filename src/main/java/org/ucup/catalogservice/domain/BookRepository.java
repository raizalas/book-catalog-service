package org.ucup.catalogservice.domain;

import java.util.List;
import java.util.Optional;


public interface BookRepository {
    List<Book> findAll();
    Optional<Book> findByIsbn(String isbn);
    boolean existsByIsbn(String Isbn);
    Book save(Book book);
    void deleteByIsbn(String Isbn);
}
