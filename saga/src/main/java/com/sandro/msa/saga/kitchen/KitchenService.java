package com.sandro.msa.saga.kitchen;

import com.sandro.msa.saga.orchestration.event.DomainEvent;
import com.sandro.msa.saga.orchestration.event.DomainEventService;
import com.sandro.msa.saga.orchestration.event.Topic;
import com.sandro.msa.saga.order.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class KitchenService {
    private final DomainEventService domainEventService;

    public void validateOrder(Order order) {
        if (order.isOk()) {
            // 주방이 주문을 받으면 주문 수락 이벤트 발행
            domainEventService.save(new DomainEvent(new ValidateResult(order.getId(), true).toString(),
                    Topic.KITCHEN_VALIDATE_RESULT));
        } else {
            // 주방에서 주문을 거절하면 주문 거절 이벤트 발행
            domainEventService.save(new DomainEvent(new ValidateResult(order.getId(), false).toString(),
                    Topic.KITCHEN_VALIDATE_RESULT));
        }
    }
}
