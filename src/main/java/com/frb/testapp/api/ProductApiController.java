package com.frb.testapp.api;

import com.frb.testapp.model.Product;
import com.frb.testapp.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@Slf4j
@RestController
public class ProductApiController {

	@Autowired
	private ProductService productService;


	@GetMapping(value = "/product/{productId}/similar")
	public ResponseEntity<Flux<Product>> getProductSimilar(@PathVariable Integer productId) {
		log.debug("Get product ID {}", productId);
		Flux<Product> productList = productService.getProductSimilar(productId);
		return new ResponseEntity<>(productList, HttpStatus.OK);
	}

}
