package microservice.ecommerce.basketservice.entities;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Basket {
    @Id
    private String id;

    private UUID productId;
    private String categoryName;
    private String productName;
    private int quantity;
    private double price;
    private double totalPrice;
    private String description;
}