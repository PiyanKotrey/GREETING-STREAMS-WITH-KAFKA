package co.draxler.greetingstreamsspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@SpringBootApplication
@EnableKafkaStreams
public class GreetingStreamsSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(GreetingStreamsSpringBootApplication.class, args);
	}

}
