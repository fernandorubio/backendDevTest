package com.frb.testapp.service;

import com.frb.testapp.exceptions.ServiceException;
import com.frb.testapp.model.Product;
import reactor.core.publisher.Flux;

public interface ProductService {

	/**
	 * Obtain a list of similar products
	 *
	 * @param productId If of the product
	 * @return List of Product entities
	 */
	Flux<Product> getProductSimilar(Integer productId);
}
