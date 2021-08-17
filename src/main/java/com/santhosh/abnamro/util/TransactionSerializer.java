package com.santhosh.abnamro.util;

import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.santhosh.abnamro.model.Transaction;

import lombok.extern.slf4j.Slf4j;

/**
 * Serializer class for Transaction object 
 *
 */
@Slf4j
public class TransactionSerializer implements Serializer<Transaction>{
	
	@Override
	public byte[] serialize(String topic, Transaction data) {
		ObjectMapper mapper = new ObjectMapper();
		byte[] retVal = null;
		try {
			retVal = mapper.writeValueAsString(data).getBytes();
		} catch (JsonProcessingException e) {
			log.error(e.getMessage());
		}
		return retVal;
	}
}
