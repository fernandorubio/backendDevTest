package com.frb.testapp.service;

import com.frb.testapp.model.Product;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductServiceTest {

	@Mock
	private ProductSimilarService productSimilarService;

	@Mock
	private ProductDetailService productDetailService;

	@Test
	void getProductSimilar() {
		Product product = mock(Product.class);
		when(productSimilarService.requestSimilar(1)).thenReturn(Flux.just(1, 2, 3));
		when(productDetailService.requestDetail(anyInt())).thenReturn(Flux.just(product));

		ProductService productService = new ProductServiceImpl(productSimilarService, productDetailService);
		Flux<Product> response = productService.getProductSimilar(1);
		assertThat(response.blockFirst()).isEqualTo(product);
	}

}