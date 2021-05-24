package com.creditcard.app.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.creditcard.app.entity.CreditCard;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, BigInteger>{
	
}
