package org.kstreamsamples;

import org.kstreamsamples.stream.EmployeeStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaStreamingApp implements CommandLineRunner{
	
	@Autowired
	EmployeeStream stream;
	 public static void main(String args[]) {
	        SpringApplication.run(KafkaStreamingApp.class);
	    }
	@Override
	public void run(String... args) throws Exception {
		stream.createTopology();
		
	}
}
