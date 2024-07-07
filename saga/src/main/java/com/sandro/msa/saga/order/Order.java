package com.sandro.msa.saga.order;

import com.sandro.msa.saga.util.ObjectMapperUtil;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Table(name = "orders")
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean ok;
    @Enumerated(EnumType.STRING)
    private State state = State.PENDING;

    public Order(boolean ok) {
        this.ok = ok;
    }

    @Override
    public String toString() {
        return ObjectMapperUtil.writeValueAsString(this);
    }

    public void updateState(boolean accepted) {
        if (accepted) {
            state = State.CONFIRMED;
        } else {
            state = State.CANCELLED;
        }
    }
}
