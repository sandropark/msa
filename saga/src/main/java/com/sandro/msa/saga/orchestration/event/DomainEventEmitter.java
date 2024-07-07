package com.sandro.msa.saga.orchestration.event;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class DomainEventEmitter {
    private final DomainEventRepository domainEventRepository;
    private final DomainEventPublisher domainEventPublisher;

    @Transactional
    @Scheduled(fixedRate = 1000)
    public void emit() {
        domainEventRepository.findAllByPublishedFalse()
                .forEach(domainEvent -> {
                    domainEventPublisher.publish(domainEvent);
                    domainEvent.published();
                });
    }
}
