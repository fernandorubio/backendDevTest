package com.frb.test.api;

import com.frb.test.exceptions.ServiceException;
import com.frb.test.model.Product;
import com.frb.test.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductApiControllerTest {

	@Mock
	private ProductService productService;


	@Autowired
	private MockMvc mockMvc;


	@BeforeEach
	public void setup() throws ServiceException {

		Product product1 = Product.builder().id("4").name("Boots").price(new BigDecimal("39.99")).availability(true).build();
		Product product2 = Product.builder().id("3").name("Blazer").price(new BigDecimal("29.99")).availability(false).build();
		Product product3 = Product.builder().id("2").name("Dress").price(new BigDecimal("19.99")).availability(false).build();

		List<Product> products = Arrays.asList(product1, product2, product3);

		when(productService.getProductSimilar(Mockito.anyInt())).thenReturn(products);
	}

	@Test
	public void getProductSimilarOK() throws ServiceException {
		ResponseEntity<List<Product>> actual = ResponseEntity.ok(productService.getProductSimilar(1));
		assertEquals(HttpStatus.OK, actual.getStatusCode());
	}

	@Test
	public void getProductSimilarKO() throws ServiceException {
		ResponseEntity<List<Product>> actual = ResponseEntity.ok(productService.getProductSimilar(null));
		assertTrue(Objects.requireNonNull(actual.getBody()).isEmpty());
	}

	@Test
	public void findByIdShouldReturnNotFoundError() throws Exception {
		this.mockMvc.perform(get("/product/6")).andDo(print()).andExpect(status().isNotFound());
	}

}