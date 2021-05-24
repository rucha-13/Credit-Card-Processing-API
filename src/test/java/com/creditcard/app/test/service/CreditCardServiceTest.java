package com.creditcard.app.test.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.creditcard.app.entity.CreditCard;
import com.creditcard.app.populate.CreditCardPopulate;
import com.creditcard.app.repository.CreditCardRepository;
import com.creditcard.app.service.CreditCardService;
import com.creditcard.app.service.impl.CreditCardServiceImpl;
import com.creditcard.app.viewModel.CreditCardDetail;

@ExtendWith(MockitoExtension.class)
public class CreditCardServiceTest {
	
	@Mock
	private CreditCardRepository repository;
	
	@InjectMocks
	private CreditCardService service = new CreditCardServiceImpl();
	
	@Mock
	private CreditCardPopulate populate;
	
	private List<CreditCardDetail> creditCardDetailList = null;
	private List<CreditCard> creditcards = null;
	
	@DisplayName("Test mock saveCard method")
	@Test
	public void givenValidCreditCardDetails_thenSaveAndReturnCreditCardDetail() throws Exception{
		CreditCard card = new CreditCard(new BigInteger("12345678903555"),"Tom", 0.0,6000.0);
		CreditCardDetail detail = new CreditCardDetail("12345678903555","Tom", 0.0,6000.0);
		lenient().when(repository.save(card)).thenReturn(new CreditCard(new BigInteger("12345678903555"),"Tom", 0,6000));
		when(populate.populateCreditCardDetail(null)).thenReturn(detail);
		CreditCardDetail cardDetail = service.saveCard(detail);
		assertThat(cardDetail).isNotNull();
	}
	
	@DisplayName("Test mock isValidCreditCardNumber method for valid credit card number")
	@Test
	public void givenValidCreditCardNumber_thenReturnTrue() throws Exception{
		boolean flag = service.isValidCreditCardNumber("12345678903555");
		assertTrue(flag);
	}
	
	@DisplayName("Test mock isValidCreditCardNumber method for invalid credit card number")
	@Test
	public void givenInValidCreditCardNumber_thenReturnFalse() throws Exception{
		boolean flag = service.isValidCreditCardNumber("12300678000500");
		assertFalse(flag);
	}
	
	@DisplayName("Test mock findAllCreditCards method")
	@Test
	public void returnAllCreditCards() throws Exception{
		creditCardDetailList = new ArrayList<>();
		creditCardDetailList.add( new CreditCardDetail("12345678903555","Tom", 0.0,6000.0));
		creditCardDetailList.add( new CreditCardDetail("012850003580200","John", 0.0,6000.0));
		creditCardDetailList.add( new CreditCardDetail("1358954993914435","Mery", 0.0,6000.0));
		creditcards = new ArrayList<>();
		creditcards.add( new CreditCard(new BigInteger("12345678903555"),"Tom", 0.0,6000.0));
		creditcards.add( new CreditCard(new BigInteger("012850003580200"),"John", 0.0,6000.0));
		creditcards.add( new CreditCard(new BigInteger ("1358954993914435"),"Mery", 0.0,6000.0));
		when(repository.findAll()).thenReturn(creditcards);
		when(populate.populateCreditCardDetailList(creditcards)).thenReturn(creditCardDetailList);
		List <CreditCardDetail>list = service.findAllCreditCards();
		assertThat(list.hashCode()).isEqualTo(creditCardDetailList.hashCode());
		  
	}
	
}
