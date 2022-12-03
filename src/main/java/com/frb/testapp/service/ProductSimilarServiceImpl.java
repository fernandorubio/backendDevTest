package com.frb.testapp.service;

import com.frb.testapp.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Slf4j
@Service
public class ProductSimilarServiceImpl implements ProductSimilarService {

	@Autowired
	private WebClient webClient;

	@Override
	public Flux<Integer> requestSimilar(Integer productId) {

		String similarIdsUrl = String.format(Constants.SERVER_URL + "/%s/similarids", productId);
		log.debug("Url similar request {}", similarIdsUrl);

		return webClient.get()
				.uri(similarIdsUrl, productId)
				.retrieve()
				.bodyToFlux(Integer.class)
				.repeat();

	}
}
