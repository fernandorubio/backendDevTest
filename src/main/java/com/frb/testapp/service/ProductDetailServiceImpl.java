package com.frb.testapp.service;

import com.frb.testapp.exceptions.ServiceException;
import com.frb.testapp.model.Product;
import com.frb.testapp.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Slf4j
@Service
public class ProductDetailServiceImpl implements ProductDetailService {

	@Autowired
	private WebClient webClient;

	@Override
	public Flux<Product> requestDetail(Integer productId) {
		return webClient.get()
				.uri(String.format(Constants.SERVER_URL + "/%s", productId))
				.retrieve()
				.onStatus(HttpStatus.INTERNAL_SERVER_ERROR::equals, response -> Mono.error(new ServiceException(ServiceError.ERROR_RETRIEVING_PRODUCT)))
				.onStatus(HttpStatus.NOT_FOUND::equals, response -> Mono.error(new ServiceException(ServiceError.PRODUCT_NOT_FOUND)))
				.bodyToFlux(Product.class)
				.timeout(Duration.ofSeconds(500000))
				.repeat()
				.onErrorResume(e -> Mono.error(new ServiceException(ServiceError.ERROR_RETRIEVING_PRODUCT)));
	}

}
