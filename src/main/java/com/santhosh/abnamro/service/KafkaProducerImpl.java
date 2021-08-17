package com.santhosh.abnamro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.santhosh.abnamro.model.Transaction;

import lombok.extern.slf4j.Slf4j;

/**
 * Kafka Producer class : Sends the message to the Topic
 *
 */
@Service
public class KafkaProducerImpl implements KafkaProducer {

	@Autowired
	private KafkaTemplate<String, Transaction> kafkaTemplate;
	
	@Value("${kafka.topic.name}")
	private String topicName;
	
	/**
	 * 	This method publishes the Transaction object to the Topic
	 * 
	 *	@param Transaction
	 *  @return void
	 */
	@Override
	public void publish(Transaction message) {
		this.kafkaTemplate.send(topicName, message);
	}
}
