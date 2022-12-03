package com.frb.testapp.service;

import com.frb.testapp.model.Product;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Slf4j
@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductSimilarService productSimilarService;

	@Autowired
	private ProductDetailService productDetailService;

	@Override
	@Cacheable("products")
	public Flux<Product> getProductSimilar(Integer productId) {

		Flux<Integer> productIdList = productSimilarService.requestSimilar(productId);

		return productIdList.flatMap(productDetailService::requestDetail);
	}


}
