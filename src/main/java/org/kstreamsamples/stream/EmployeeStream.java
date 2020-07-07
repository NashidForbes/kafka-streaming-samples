package org.kstreamsamples.stream;

import java.util.Properties;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Printed;
import org.kstreamsamples.serdes.AppSerdes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class EmployeeStream {

	@Autowired
	private Environment env;

	public void createTopology() {
		Properties props = new Properties();
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, env.getProperty("application.id"));
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, env.getProperty("bootstrap.servers"));
		props.put(StreamsConfig.STATE_DIR_CONFIG, env.getProperty("statestore.location"));

		StreamsBuilder streamsBuilder = new StreamsBuilder();

		streamsBuilder.stream(env.getProperty("source.topic"), Consumed.with(Serdes.String(), AppSerdes.Employee()))
				.print(Printed.toSysOut());

		KafkaStreams streams = new KafkaStreams(streamsBuilder.build(), props);
		streams.start();

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			streams.close();
		}));
	}

}
