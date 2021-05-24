package com.creditcard.app.populate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.creditcard.app.entity.CreditCard;
import com.creditcard.app.viewModel.CreditCardDetail;

@Component
public class CreditCardPopulate {
	
	public List<CreditCardDetail> populateCreditCardDetailList(List<CreditCard> creditCardList){
		List<CreditCardDetail> creditCardDetails = new ArrayList<>();
		for(int i = 0; i< creditCardList.size(); i++) {
			CreditCard creditCard = creditCardList.get(i);
			CreditCardDetail creditCardDetail = new CreditCardDetail();
			creditCardDetail.setBalance(creditCard.getBalance());
			creditCardDetail.setCreditCardNumber(creditCard.getCreditCardNumber().toString());
			creditCardDetail.setName(creditCard.getName());
			creditCardDetail.setLimit(creditCard.getLimit());
			creditCardDetails.add(creditCardDetail);
		}
		return creditCardDetails;
	}
	
	public CreditCardDetail populateCreditCardDetail(CreditCard creditCard) {
		CreditCardDetail creditCardDetail = new CreditCardDetail();
		creditCardDetail.setBalance(creditCard.getBalance());
		creditCardDetail.setCreditCardNumber(creditCard.getCreditCardNumber().toString());
		creditCardDetail.setName(creditCard.getName());
		creditCardDetail.setLimit(creditCard.getLimit());
		return creditCardDetail;
	}

}
