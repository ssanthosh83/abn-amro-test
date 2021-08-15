package com.santhosh.abnamro.service;

import com.santhosh.abnamro.model.Transaction;

public interface KafkaProducer {
	void publish(Transaction message);
}
