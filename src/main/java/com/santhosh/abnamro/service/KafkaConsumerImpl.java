package com.santhosh.abnamro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.santhosh.abnamro.model.Transaction;
import com.santhosh.abnamro.repository.TransactionRepository;

/**
 * Kafka Consumer class : Consumes the message and saves into the Database
 *
 */
@Service
public class KafkaConsumerImpl implements KafkaConsumer {

	@Autowired
	private TransactionRepository repository;

	
	/**
	 * 	This method consumes the Transaction object and stores in the database
	 * 
	 *	@param Transaction
	 *  @return void
	 */
	@KafkaListener(topics = "${kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
	public void consume(Transaction message) {
		this.repository.save(message);
	}
	
}
