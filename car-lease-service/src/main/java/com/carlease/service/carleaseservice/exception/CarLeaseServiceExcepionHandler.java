package com.carlease.service.carleaseservice.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**Customized Exception handler class -
 * When the request fails the response entity is
 * Customized for a proper message to the user
 * @author Shruthi
 *
 */
@ControllerAdvice
@RestController
public class CarLeaseServiceExcepionHandler extends ResponseEntityExceptionHandler {
	
		/*
		 * Handles all Exceptions thrown during a web request
		 */
		@ExceptionHandler(Exception.class)
		public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request){
			ExceptionResponse exceptionResponse = 
					new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
			
			return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		/*
		 * Handles exection thrown when customer is not found
		 */
		@ExceptionHandler(CustomerNotFoundException.class)
		public final ResponseEntity<Object> handleUserNotFoundException(CustomerNotFoundException ex, WebRequest request){
			ExceptionResponse exceptionResponse = 
					new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
			
			return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
		}
		
		/*
		 * Handles invalid parameter exection thrown when entity validation fails
		 */
		@Override
		protected ResponseEntity<Object> handleMethodArgumentNotValid(
				MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
			ExceptionResponse exceptionResponse = 
					new ExceptionResponse(new Date(), "Validation Failed", ex.getBindingResult().toString());
			
			return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
		}

}
