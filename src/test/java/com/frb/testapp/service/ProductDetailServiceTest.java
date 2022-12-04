package com.frb.testapp.service;

import com.frb.testapp.model.Product;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductDetailServiceTest {

	@Mock
	private ProductDetailService productDetailService;

	@Test
	void requestDetail() {
		Product productDTO = mock(Product.class);
		when(productDTO.getId()).thenReturn("1");
		when(productDTO.getName()).thenReturn("productName");
		when(productDTO.getPrice()).thenReturn(BigDecimal.ONE);
		when(productDTO.getAvailability()).thenReturn(true);
		when(productDetailService.requestDetail(anyInt())).thenReturn(Flux.just(productDTO));

		Flux<Product> response = productDetailService.requestDetail(1);
		StepVerifier
				.create(response)
				.expectNext(productDTO)
				.expectComplete()
				.verify();
	}
}