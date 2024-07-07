package com.sandro.msa.saga.order;

import com.sandro.msa.saga.orchestration.event.Topic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@TestPropertySource(properties = {
        "spring.kafka.bootstrap-servers=${spring.embedded.kafka.brokers}",
        "spring.kafka.producer.bootstrap-servers=${spring.embedded.kafka.brokers}",
        "spring.kafka.admin.properties.bootstrap.servers=${spring.embedded.kafka.brokers}"
})
@EmbeddedKafka(topics = {Topic.CREATE_ORDER, Topic.KITCHEN_VALIDATE_RESULT})
@SpringBootTest
class OrderServiceTest {
    @Autowired
    KafkaListenerEndpointRegistry endpointRegistry;
    @Autowired
    EmbeddedKafkaBroker embeddedKafkaBroker;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        endpointRegistry.getAllListenerContainers().forEach(messageListenerContainer -> {
            if (messageListenerContainer.isAutoStartup())
                ContainerTestUtils.waitForAssignment(messageListenerContainer, embeddedKafkaBroker.getPartitionsPerTopic());
        });
    }

    @Test
    void createOrder() throws Exception {
        // Given
        orderService.createOrder(true);

        // When
        Thread.sleep(3000);

        // Then
        List<Order> all = orderRepository.findAll();
        assertThat(all).hasSize(1);
        assertThat(all.get(0).getState()).isEqualTo(State.CONFIRMED);
    }

}