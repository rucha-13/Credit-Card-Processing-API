package com.creditcard.app.service.impl;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditcard.app.entity.CreditCard;
import com.creditcard.app.populate.CreditCardPopulate;
import com.creditcard.app.repository.CreditCardRepository;
import com.creditcard.app.service.CreditCardService;
import com.creditcard.app.viewModel.CreditCardDetail;

@Service
public class CreditCardServiceImpl implements CreditCardService {
	
	@Autowired
	CreditCardRepository repository;
	
	@Autowired
	CreditCardPopulate creditCardPopulate;

	@Override
	public boolean isValidCreditCardNumber(String creditCardNumber) {
		int len = creditCardNumber.length();
		int[] nums = new int[len];
		
		for (int i = 0; i < len; i++) {
			nums[i] = Integer.parseInt("" + creditCardNumber.charAt(i));
		}

		for (int i = len - 2; i >= 0; i = i - 2) {
			int digit = nums[i];
			digit = digit * 2;
			if (digit > 9)
				digit = digit % 10 + 1;
			nums[i] = digit;
		}
		int sum = Arrays.stream(nums).sum();
		return sum % 10 == 0;
	}

	@Override
	@Transactional
	public CreditCardDetail saveCard(CreditCardDetail creditCardDetail) {
		CreditCard card = new CreditCard(new BigInteger(creditCardDetail.getCreditCardNumber()),creditCardDetail.getName(),0,creditCardDetail.getLimit());
		card =  repository.save(card);
		return creditCardPopulate.populateCreditCardDetail(card);
	}
	
	@Override
	public List<CreditCardDetail> findAllCreditCards() {
		List<CreditCard> creditCards = repository.findAll();
		if(creditCards == null)
			return null;
		else
			return creditCardPopulate.populateCreditCardDetailList(creditCards);
	}
		

}
