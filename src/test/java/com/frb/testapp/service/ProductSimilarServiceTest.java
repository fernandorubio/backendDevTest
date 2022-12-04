package com.frb.testapp.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductSimilarServiceTest {

	@Mock
	private ProductSimilarService productSimilarService;

	@Test
	void requestSimilar() {
		when(productSimilarService.requestSimilar(anyInt())).thenReturn(Flux.just(1, 2, 3));
		Flux<Integer> response = productSimilarService.requestSimilar(2);
		StepVerifier
				.create(response)
				.expectNext(1, 2, 3)
				.expectComplete()
				.verify();
	}
}