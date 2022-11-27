package com.frb.test.exceptions;

import com.frb.test.service.ServiceError;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServiceException extends Exception {

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
