package org.ucup.catalogservice.persistence;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;
import org.ucup.catalogservice.domain.Book;
import org.ucup.catalogservice.domain.BookRepository;

@Repository
public class InMemoryBookRepository implements BookRepository {
    private static final Map<String, Book> books = new ConcurrentHashMap<>();

    @Override
    public List<Book> findAll() {
        return books.values()
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return existsByIsbn(isbn) ? Optional.of(books.get(isbn)) : Optional.empty();
    }

    @Override
    public void deleteByIsbn(String Isbn) {
        books.remove(Isbn);

    }

    @Override
    public boolean existsByIsbn(String Isbn) {
        return books.get(Isbn) != null;
    }

    @Override
    public Book save(Book book) {

        books.put(book.isbn(), book);
        return book;
    }

}
