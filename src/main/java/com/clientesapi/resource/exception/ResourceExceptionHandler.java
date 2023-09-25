package com.clientesapi.resource.exception;

import java.util.List;
import java.util.stream.Collectors;

import com.clientesapi.service.exception.DataIntegratyViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ApiErrors handleValidationErrors(MethodArgumentNotValidException ex) {
		BindingResult bindingResult = ex.getBindingResult();
		List<String> messages = bindingResult.getAllErrors()
									.stream()
									.map(objectError -> objectError.getDefaultMessage())
									.collect(Collectors.toList());
		
		return new ApiErrors(messages);
	}

	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<?> handleResponseStatusException(ResponseStatusException ex) {
		String mensagemErro = ex.getMessage();
		HttpStatus codigoStatus = ex.getStatus();		
		ApiErrors apiErrors = new ApiErrors(mensagemErro);
		
		return new ResponseEntity(apiErrors, codigoStatus);
	}

	@ExceptionHandler(DataIntegratyViolationException.class)
	public ResponseEntity<StandardError> dataIntegratyViolationException(DataIntegratyViolationException e, HttpServletRequest request){
		StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				"Data Integraty Violation Exception", e.getMessage(), request.getRequestURI());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
}