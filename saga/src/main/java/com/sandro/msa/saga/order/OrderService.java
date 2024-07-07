package com.sandro.msa.saga.order;

import com.sandro.msa.saga.kitchen.ValidateResult;
import com.sandro.msa.saga.orchestration.event.DomainEvent;
import com.sandro.msa.saga.orchestration.event.DomainEventService;
import com.sandro.msa.saga.orchestration.event.Topic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final DomainEventService domainEventService;

    @Transactional
    public void createOrder(boolean ok) {
        // 주문 생성
        Order order = new Order(ok);
        // 주문 저장
        orderRepository.save(order);
        // 주문 생성 이벤트 발행
        domainEventService.save(new DomainEvent(order.toString(), Topic.CREATE_ORDER));
    }

    @Transactional
    public void updateOrderState(ValidateResult validateResult) {
        Order order = orderRepository.findById(validateResult.orderId())
                .orElseThrow(IllegalArgumentException::new);
        order.updateState(validateResult.accepted());
    }
}
