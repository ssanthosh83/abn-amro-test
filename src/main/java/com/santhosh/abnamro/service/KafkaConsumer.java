package com.santhosh.abnamro.service;

import com.santhosh.abnamro.model.Transaction;

public interface KafkaConsumer {

	void consume(Transaction message);
}
