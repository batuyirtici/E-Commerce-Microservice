package microservice.ecommerce.basketservice.business.abstracts;

import microservice.ecommerce.basketservice.business.dto.responses.GetAllBasketsResponse;
import microservice.ecommerce.basketservice.business.dto.responses.GetBasketResponse;
import microservice.ecommerce.commonpackage.dto.CreateSalePaymentRequest;
import microservice.ecommerce.commonpackage.dto.basket.BasketTotalPriceResponse;
import microservice.ecommerce.commonpackage.dto.basket.KafkaBasketResponse;
import microservice.ecommerce.commonpackage.events.basket.BasketCreatedEvent;
import microservice.ecommerce.commonpackage.utils.dto.ClientResponse;

import java.util.List;

public interface BasketService {
    List<GetAllBasketsResponse> getAll();
    GetBasketResponse getById(String id);
    KafkaBasketResponse add(BasketCreatedEvent event);
    ClientResponse controlBasketService();
    void delete(String id);
    void deleteAll();
    BasketTotalPriceResponse basketTotalPrice ();
    KafkaBasketResponse buyCart(CreateSalePaymentRequest request);
}