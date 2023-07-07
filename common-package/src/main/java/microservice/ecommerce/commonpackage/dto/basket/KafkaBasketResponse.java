package microservice.ecommerce.commonpackage.dto.basket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KafkaBasketResponse {
    private UUID productId;
    private String categoryName;
    private String productName;
    private int quantity;
    private double price;
    private double totalPrice;
    private String description;
}