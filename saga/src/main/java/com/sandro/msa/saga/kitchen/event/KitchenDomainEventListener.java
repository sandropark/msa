package com.sandro.msa.saga.kitchen.event;

import com.sandro.msa.saga.kitchen.KitchenService;
import com.sandro.msa.saga.orchestration.event.Topic;
import com.sandro.msa.saga.order.Order;
import com.sandro.msa.saga.util.ObjectMapperUtil;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class KitchenDomainEventListener {
    private final KitchenService kitchenService;

    @KafkaListener(topics = Topic.CREATE_ORDER, groupId = "kitchen_create_order")
    public void listenOrderCreatedEvent(ConsumerRecord<String, String> record) {
        Order order = ObjectMapperUtil.readValue(record.value(), Order.class);
        kitchenService.validateOrder(order);
    }
}
