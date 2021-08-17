package com.santhosh.abnamro.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.santhosh.abnamro.model.Transaction;
import com.santhosh.abnamro.repository.TransactionRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * Kafka Consumer class : Consumes the message and saves into the Database
 *
 */
@Service
public class KafkaConsumerImpl implements KafkaConsumer {

	@Autowired
	private TransactionRepository repository;
	
	@Value("${kafka.topic.name}")
	private String topicName;

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
