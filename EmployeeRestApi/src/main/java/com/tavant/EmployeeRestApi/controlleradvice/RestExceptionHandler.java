package com.tavant.EmployeeRestApi.controlleradvice;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.tavant.EmployeeRestApi.errorresponse.ApiError;

class RestExceptionHandler extends ResponseEntityExceptionHandler{
	
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
	HttpHeaders header, HttpStatus status, WebRequest request) {
		
		
		System.err.println("hello from My project");
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
		
		apiError.addValidationErrors(ex.getFieldErrors());
		return buildResponseEntity(apiError);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	protected ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException exception) {
		
		
		System.out.println("inside exception");
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
		apiError.setMessage("Validation Error");
		
		apiError.addValidationErrors(exception.getConstraintViolations());
		return buildResponseEntity(apiError);
	}
	
	private ResponseEntity<Object> buildResponseEntity(ApiError apiError){
		return new ResponseEntity<Object>(apiError,apiError.getStatus());
	}
}
