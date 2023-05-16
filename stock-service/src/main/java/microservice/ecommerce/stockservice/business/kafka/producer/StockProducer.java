package microservice.ecommerce.stockservice.business.kafka.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.ecommerce.commonpackage.events.stock.ProductCreatedEvent;
import microservice.ecommerce.commonpackage.events.stock.ProductDeletedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(ProductCreatedEvent event){
        log.info(String.format("product-create event => %s", event.toString()));
        Message<ProductCreatedEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, "product-created")
                .build();

        kafkaTemplate.send(message);
    }

    public void sendMessage(ProductDeletedEvent event){
        log.info(String.format("product-delete event => %s", event.toString()));
        Message<ProductDeletedEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, "product-deleted")
                .build();

        kafkaTemplate.send(message);
    }
}