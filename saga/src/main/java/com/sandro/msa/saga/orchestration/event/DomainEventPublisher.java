package com.sandro.msa.saga.orchestration.event;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DomainEventPublisher {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void publish(DomainEvent event) {
        kafkaTemplate.send(event.getTopic(), event.getData());
    }
}
