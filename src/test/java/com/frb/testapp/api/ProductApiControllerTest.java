package com.frb.testapp.api;

import com.frb.testapp.model.Product;
import com.frb.testapp.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@WebFluxTest(ProductApiController.class)
class ProductApiControllerTest {

	@Autowired
	WebTestClient webTestClient;

	@MockBean
	ProductService productService;

	@Test
	void getProductSimilar() {
		when(productService.getProductSimilar(anyInt())).thenReturn(Flux.fromIterable(List.of(new Product())));

		webTestClient.get()
				.uri("/product/1/similar")
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$").isArray();
	}
}