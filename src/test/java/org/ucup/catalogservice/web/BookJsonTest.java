package org.ucup.catalogservice.web;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.ucup.catalogservice.domain.Book;

@JsonTest
public class BookJsonTest {

    @Autowired
    private JacksonTester<Book> json;

    @Test
    void testSerialize() throws Exception{
        Instant now = Instant.now();
        Book book = new Book(123L,"1231231231", "Title", "Author", 9.90, null ,now, now, 24);
        JsonContent<Book> jsonContent = json.write(book);
        assertThat(jsonContent).extractingJsonPathNumberValue("@.id")
            .isEqualTo(book.id().intValue());
        assertThat(jsonContent).extractingJsonPathStringValue("@.isbn")
            .isEqualTo(book.isbn());
        assertThat(jsonContent).extractingJsonPathStringValue("@.title")
            .isEqualTo(book.title());
        assertThat(jsonContent).extractingJsonPathStringValue("@.author")
            .isEqualTo(book.author());
        assertThat(jsonContent).extractingJsonPathNumberValue("@.price")
            .isEqualTo(book.price());
        assertThat(jsonContent).extractingJsonPathStringValue("@.createdDate")
            .isEqualTo(book.createdDate().toString());
        assertThat(jsonContent).extractingJsonPathStringValue("@.lastModifiedDate")
            .isEqualTo(book.lastModifiedDate().toString());
        assertThat(jsonContent).extractingJsonPathNumberValue("@.version")
            .isEqualTo(book.version());
    }

    @Test
    void testDeserialize() throws Exception {
        Instant instant = Instant.parse("2021-09-07T22:50:37.135029Z");;
        var content = """
                {
                    "id": 123,
                    "isbn": "1234567890",
                    "title": "Title",
                    "author": "Author",
                    "price": 9.90,
                    "createdDate": "2021-09-07T22:50:37.135029Z",
                    "lastModifiedDate": "2021-09-07T22:50:37.135029Z",
                    "version": 21
                }
                """;
        assertThat(json.parse(content))
            .usingRecursiveComparison()
            .isEqualTo(new Book(123L,"1234567890", "Title", "Author", 9.90,null, instant, instant, 21));
    }
}
