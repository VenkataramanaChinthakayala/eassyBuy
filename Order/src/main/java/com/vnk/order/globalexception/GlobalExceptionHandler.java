
package com.vnk.order.globalexception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception exception, WebRequest webRequest) {
		return null;
	}
	
	@ExceptionHandler(OrderException.class)
	public ResponseEntity<ErrorResponse> handleOrderException(OrderException exception, WebRequest webRequest) {
		ErrorResponse errorResponse=new ErrorResponse();
		errorResponse.setError(exception.getClass().getName());
		errorResponse.setMessage(exception.getMessage());
		errorResponse.setPath(webRequest.getDescription(false));
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		errorResponse.setTimestamp(LocalDateTime.now());
		
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.BAD_REQUEST);
	}
	
}
