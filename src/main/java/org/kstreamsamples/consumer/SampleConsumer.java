package org.kstreamsamples.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SampleConsumer {
	

	@KafkaListener(topics = "${consumer.topic}")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
    }
}
