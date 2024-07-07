package com.sandro.msa.saga.orchestration.event;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class DomainEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String topic;
    private String data;
    private boolean published;

    public DomainEvent(String data, String topic) {
        this.data = data;
        this.published = false;
        this.topic = topic;
    }

    public void published() {
        this.published = true;
    }
}
