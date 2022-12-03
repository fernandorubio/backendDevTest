package com.frb.testapp.service;

import com.frb.testapp.model.Product;
import reactor.core.publisher.Flux;

public interface ProductDetailService {

	/**
	 * Make a request to obtain the detail of a product
	 *
	 * @param productId Id of the product
	 * @return Product entity
	 */
	Flux<Product> requestDetail(Integer productId);
}
