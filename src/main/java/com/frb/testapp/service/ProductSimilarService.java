package com.frb.testapp.service;

import reactor.core.publisher.Flux;

public interface ProductSimilarService {

	/**
	 * Request to obtain the similar products ids
	 *
	 * @param productId Id of the product
	 * @return List of product ids
	 */
	Flux<Integer> requestSimilar(Integer productId);
}
