package org.kstreamsamples.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SampleConsumer {
	
	@Autowired
	private Environment env;

	@KafkaListener(topics = "simple-invoice")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
    }
}
