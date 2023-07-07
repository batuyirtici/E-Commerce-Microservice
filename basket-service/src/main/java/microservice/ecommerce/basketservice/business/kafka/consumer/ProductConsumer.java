package microservice.ecommerce.basketservice.business.kafka.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.ecommerce.basketservice.business.abstracts.BasketService;
import microservice.ecommerce.commonpackage.events.basket.BasketCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductConsumer {
    private final BasketService service;

    @KafkaListener(
            topics = "basket-created",
            groupId = "basket-create"
    )
    public void consume(BasketCreatedEvent event){
        service.add(event);
        log.info("Basket created event consumed {}", event);
    }
}