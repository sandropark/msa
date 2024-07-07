package com.sandro.msa.saga.orchestration.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DomainEventService {
    private final DomainEventRepository domainEventRepository;

    public void save(DomainEvent domainEvent) {
        domainEventRepository.save(domainEvent);
    }
}
