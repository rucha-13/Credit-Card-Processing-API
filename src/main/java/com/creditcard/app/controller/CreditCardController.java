package com.creditcard.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.creditcard.app.exception.InvalidCreditCardNumberException;
import com.creditcard.app.service.CreditCardService;
import com.creditcard.app.viewModel.CreditCardDetail;

import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@RequestMapping(path = "/creditcard",consumes = "application/json", produces = "application/json")
public class CreditCardController {
	
	@Autowired
	private CreditCardService service;
	
	@ApiOperation(value="Creats entry for credit card in the table")
	@PostMapping("")
	public ResponseEntity<CreditCardDetail> create(@RequestBody CreditCardDetail creditCardDetail) {
		if(! service.isValidCreditCardNumber(creditCardDetail.getCreditCardNumber()))
			throw new InvalidCreditCardNumberException();
		return ResponseEntity.ok(service.saveCard(creditCardDetail));
	}
	
	@ApiOperation(value="Finds all the credit cards from the table")
	@GetMapping("")
	public ResponseEntity<Object> getAllCards() {
		List cards = service.findAllCreditCards();
		if(cards == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Credit card  List is empty.");
		return ResponseEntity.status(HttpStatus.FOUND).body(cards);
	}
}
