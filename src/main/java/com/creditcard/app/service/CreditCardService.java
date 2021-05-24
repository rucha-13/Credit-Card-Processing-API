package com.creditcard.app.service;

import java.util.List;

import com.creditcard.app.viewModel.CreditCardDetail;

public interface CreditCardService {
	public boolean isValidCreditCardNumber(String creditCardNumber);
	public CreditCardDetail saveCard(CreditCardDetail creditCardDetail);
	public List<CreditCardDetail> findAllCreditCards();
}
