package microservice.ecommerce.commonpackage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import microservice.ecommerce.commonpackage.dto.basket.KafkaBasketResponse;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaleBasketResponse {
    private List<KafkaBasketResponse> basketProducts;
    private double basketTotalPrice;
}
