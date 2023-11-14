package co.draxler.greetingstreamsspringboot.config;

import co.draxler.greetingstreamsspringboot.topology.GreetingStreamsTopology;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class GreetingStreamsConfiguration {

    @Bean
    public NewTopic greetingsTopic(){
        return TopicBuilder.name(GreetingStreamsTopology.GREETINGS)
                .partitions(2)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic greetings_outTopic(){
        return TopicBuilder.name(GreetingStreamsTopology.GREETINGS_OUT)
                .partitions(2)
                .replicas(1)
                .build();
    }

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }
}
