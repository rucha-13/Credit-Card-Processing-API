package com.creditcard.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class InvalidCreditCardNumberExceptionController {

	@ExceptionHandler(value = InvalidCreditCardNumberException.class)
	public ResponseEntity<Object> exception(InvalidCreditCardNumberException exception) {
		return new ResponseEntity<>("Credit card number is not valid.", HttpStatus.NOT_ACCEPTABLE);
	}
}
