package microservice.ecommerce.basketservice.api.controllers;

import lombok.AllArgsConstructor;
import microservice.ecommerce.basketservice.business.abstracts.BasketService;
import microservice.ecommerce.basketservice.business.dto.responses.GetAllBasketsResponse;
import microservice.ecommerce.basketservice.business.dto.responses.GetBasketResponse;
import microservice.ecommerce.commonpackage.dto.CreateSalePaymentRequest;
import microservice.ecommerce.commonpackage.dto.basket.BasketTotalPriceResponse;
import microservice.ecommerce.commonpackage.dto.basket.KafkaBasketResponse;
import microservice.ecommerce.commonpackage.utils.dto.ClientResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/baskets")
public class BasketsController {
    private final BasketService service;

    @GetMapping
    public List<GetAllBasketsResponse> getAll()
    { return service.getAll(); }

    @GetMapping("/{id}")
    public GetBasketResponse getById(@PathVariable String id)
    { return service.getById(id); }

    @GetMapping("/totalPrice")
    public BasketTotalPriceResponse basketTotalPrice()
    { return service.basketTotalPrice(); }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id)
    { service.delete(id); }

    @DeleteMapping("/deleteAll")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll()
    { service.deleteAll(); }

    @GetMapping("/control-basket-service")
    ClientResponse controlBasketService()
    { return service.controlBasketService();}

    @PostMapping
    public KafkaBasketResponse buyCart(@RequestBody CreateSalePaymentRequest request)
    { return service.buyCart(request); }
}