package com.frb.test.api;

import com.frb.test.exceptions.ServiceException;
import com.frb.test.model.Product;
import com.frb.test.service.ProductService;
import com.frb.test.service.ServiceError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
public class ProductApiController {

	@Autowired
	private ProductService productService;


	@Operation(summary = "Similar products", description = "Search similar products", tags = {"Product"})
	@ApiResponses({
			@ApiResponse(responseCode = Errors.PRODUCT_NOT_FOUND, description = Errors.PRODUCT_NOT_FOUND_MSG),
			@ApiResponse(responseCode = Errors.ERROR_RETRIEVING_PRODUCT, description = Errors.ERROR_RETRIEVING_PRODUCT_MSG),
			@ApiResponse(responseCode = "200", description = "Product recovered successfully.")
	})
	@GetMapping(value = "/product/{productId}/similar")
	public ResponseEntity<List<Product>> getProductSimilar(@PathVariable Integer productId) {
		log.debug("Get product ID {}", productId);
		List<Product> productList = null;
		try {
			productList = productService.getProductSimilar(productId);
		} catch (ServiceException e) {
			if (Objects.equals(e.getServiceError().getCode(), ServiceError.PRODUCT_NOT_FOUND.getCode())) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else if (Objects.equals(e.getServiceError().getCode(), ServiceError.ERROR_RETRIEVING_PRODUCT.getCode())
					|| Objects.equals(e.getServiceError().getCode(), ServiceError.ERROR_PARSING_PRODUCT.getCode())) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return new ResponseEntity<>(productList, HttpStatus.OK);
	}

}
