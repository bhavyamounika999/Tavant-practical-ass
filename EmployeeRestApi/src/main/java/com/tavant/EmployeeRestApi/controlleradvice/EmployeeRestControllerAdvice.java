package com.tavant.EmployeeRestApi.controlleradvice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.tavant.EmployeeRestApi.errorresponse.ErrorResponse;
import com.tavant.EmployeeRestApi.exception.EmployeeNotFoundException;
import com.tavant.EmployeeRestApi.exception.NoDataFoundException;

@ControllerAdvice
public class EmployeeRestControllerAdvice {
	
	@ExceptionHandler(EmployeeNotFoundException.class)
	
	public final ResponseEntity<ErrorResponse>
	handleEmployeeNotFoundException(NoDataFoundException e, WebRequest request){
		List<String> details = new ArrayList<String>();
		details.add(e.getLocalizedMessage());
		
		ErrorResponse errorResponse = new ErrorResponse("INCORRECT REQUEST", details);
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

}
