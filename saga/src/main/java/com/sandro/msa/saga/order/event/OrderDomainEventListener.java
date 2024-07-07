package com.sandro.msa.saga.order.event;

import com.sandro.msa.saga.kitchen.ValidateResult;
import com.sandro.msa.saga.orchestration.event.Topic;
import com.sandro.msa.saga.order.OrderService;
import com.sandro.msa.saga.util.ObjectMapperUtil;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderDomainEventListener {
    private final OrderService orderService;

    @KafkaListener(topics = Topic.KITCHEN_VALIDATE_RESULT, groupId = "order_validate_result")
    public void listenOrderCreatedEvent(ConsumerRecord<String, String> record) {
        ValidateResult validateResult = ObjectMapperUtil.readValue(record.value(), ValidateResult.class);
        orderService.updateOrderState(validateResult);
    }
}
