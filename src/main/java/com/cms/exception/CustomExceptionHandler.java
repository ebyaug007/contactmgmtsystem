package com.cms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler({InvalidPhoneNumber.class})
	public ResponseEntity<Object> handleInvalidPhoneNumber(Exception ex, WebRequest wr)
	{
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
	}
}
