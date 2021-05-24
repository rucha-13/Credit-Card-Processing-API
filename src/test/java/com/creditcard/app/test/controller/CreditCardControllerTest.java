package com.creditcard.app.test.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.creditcard.app.controller.CreditCardController;
import com.creditcard.app.service.CreditCardService;
import com.creditcard.app.viewModel.CreditCardDetail;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebMvcTest(CreditCardController.class)
@ActiveProfiles("test")
public class CreditCardControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CreditCardService service;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void givenValidCreditCardDetails_thenReturnSucceed() throws Exception {
		CreditCardDetail creditCardDetail = new CreditCardDetail("1358954993914435","John",0,4000);	
		given(service.isValidCreditCardNumber(creditCardDetail.getCreditCardNumber())).willReturn(true);
		given(service.saveCard(creditCardDetail)).willReturn(creditCardDetail);
		this.mockMvc.perform(post("/creditcard").accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(creditCardDetail)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void givenInValidCreditCardDetails_thenReturnException() throws Exception {
		CreditCardDetail creditCardDetail = new CreditCardDetail("1050054993004400","John",0,4000);	
		given(service.isValidCreditCardNumber(creditCardDetail.getCreditCardNumber())).willReturn(false);
		given(service.saveCard(creditCardDetail)).willReturn(creditCardDetail);
		this.mockMvc.perform(post("/creditcard").accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(creditCardDetail)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is(406));
	}
	
	@Test
	public void getAllCreditCardDetails() throws Exception {
		List <CreditCardDetail>creditCardDetailList = new ArrayList<>();
		creditCardDetailList.add( new CreditCardDetail("12345678903555","Tom", 0.0,6000.0));
		creditCardDetailList.add( new CreditCardDetail("012850003580200","John", 0.0,6000.0));
		creditCardDetailList.add( new CreditCardDetail("1358954993914435","Mery", 0.0,6000.0));
		
		given(service.findAllCreditCards()).willReturn(creditCardDetailList);
		this.mockMvc.perform(get("/creditcard").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isFound());
	}
	
	@Test
	public void getAllCreditCards_whenCreditCardTableIsEmpty_ReturnNull() throws Exception {
		given(service.findAllCreditCards()).willReturn(null);
		this.mockMvc.perform(get("/creditcard").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}
}
