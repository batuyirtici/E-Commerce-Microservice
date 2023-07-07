package microservice.ecommerce.commonpackage.events.basket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import microservice.ecommerce.commonpackage.events.Event;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BasketCreatedEvent implements Event {
    private UUID productId;
    private String categoryName;
    private String productName;
    private int quantity;
    private double price;
    private double totalPrice;
    private String description;
}