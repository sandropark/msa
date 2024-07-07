package com.sandro.msa.saga.orchestration.event;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DomainEventRepository extends JpaRepository<DomainEvent, Long> {
    List<DomainEvent> findAllByPublishedFalse();
}
