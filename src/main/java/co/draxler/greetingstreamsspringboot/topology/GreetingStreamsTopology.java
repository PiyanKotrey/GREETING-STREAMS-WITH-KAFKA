package co.draxler.greetingstreamsspringboot.topology;

import co.draxler.greetingstreamsspringboot.domain.Greeting;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Printed;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GreetingStreamsTopology {
    private final ObjectMapper objectMapper;
    public static String GREETINGS = "greetings";
    public static String GREETINGS_OUT = "greetings_out";

    public GreetingStreamsTopology(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Autowired
    public void process (StreamsBuilder streamsBuilder){
    var greetingsStream = streamsBuilder
            .stream(GREETINGS, Consumed.with(Serdes.String(),
//                    Serdes.String())
                        new JsonSerde<>(Greeting.class,objectMapper))
            );

    greetingsStream
//            .print(Printed.<String,String>toSysOut().withLabel("greetingsStream"));
            .print(Printed.<String,Greeting>toSysOut().withLabel("greetingsStream"));

    var modifiedStream = greetingsStream
            .mapValues((readOnlyKey, value) ->
//                    value.toUpperCase()
                    new Greeting(value.message().toUpperCase(),value.localDateTime())
            );

    modifiedStream
//            .print(Printed.<String,String>toSysOut().withLabel("modifiedStream"));
            .print(Printed.<String,Greeting>toSysOut().withLabel("modifiedStream"));

    modifiedStream
//            .to(GREETINGS_OUT, Produced.with(Serdes.String(),Serdes.String()));
            .to(GREETINGS_OUT, Produced.with(Serdes.String(),new JsonSerde<>(Greeting.class,objectMapper))
            );
    }
}

