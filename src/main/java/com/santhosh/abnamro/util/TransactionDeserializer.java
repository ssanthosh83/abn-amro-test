package com.santhosh.abnamro.util;

import java.io.IOException;

import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santhosh.abnamro.model.Transaction;

/**
 * Deserializer class for Transaction object 
 *
 */
public class TransactionDeserializer implements Deserializer<Transaction> {

private static final Logger log = LoggerFactory.getLogger(TransactionDeserializer.class);

	@Override
	public Transaction deserialize(String topic, byte[] data) {
		ObjectMapper mapper = new ObjectMapper();
		Transaction transaction = null;
			try {
				transaction = mapper.readValue(data, Transaction.class);
			} catch (IOException e) {
				log.error(e.getMessage());
			}
		return transaction;
	}
}
