package microservice.ecommerce.basketservice.business.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetBasketResponse {
    private String id;
    private UUID productId;
    private String categoryName;
    private String productName;
    private int quantity;
    private double price;
    private String description;
}
