package com.clientesapi.resource.exception;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

public class ApiErrors {

	@Getter
	List<String> errors;

	public ApiErrors(List<String> errors) {
		super();
		this.errors = errors;
	}

	public ApiErrors(String message) {
		super();
		this.errors = Arrays.asList(message);
	}

}
