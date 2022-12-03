package com.frb.testapp.exceptions;

import com.frb.testapp.service.ServiceError;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServiceException extends RuntimeException {

	private final ServiceError serviceError;

	public ServiceException(ServiceError serviceError) {
		super();
		this.serviceError = serviceError;
		log.error(serviceError.getDescription());
	}

	public ServiceError getServiceError() {
		return serviceError;
	}
}
