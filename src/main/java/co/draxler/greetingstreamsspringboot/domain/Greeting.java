package co.draxler.greetingstreamsspringboot.domain;

import org.apache.kafka.common.protocol.types.Field;

import java.time.LocalDateTime;

public record Greeting(
        String message,
        LocalDateTime localDateTime
) {
}
