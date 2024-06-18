package org.ucup.catalogservice;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.ucup.catalogservice.domain.Book;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CatalogserviceApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void contextLoads() {
	}

	@Test
	@DisplayName("POST request to the /books endpoint.")
	void whenPostRequestThenBookCreated() {
		Book expectedBook = new Book("1231231231", "Title", "Author", 9.90);

		webTestClient.post()
				.uri("/books")
				.bodyValue(expectedBook)
				.exchange()
				.expectStatus().isCreated()
				.expectBody(Book.class).value(actualBook -> {
					assertThat(actualBook).isNotNull();
					assertThat(actualBook.isbn()).isEqualTo(expectedBook.isbn());
				});
	}

}
