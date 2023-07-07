package microservice.ecommerce.basketservice.business.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import microservice.ecommerce.basketservice.business.abstracts.BasketService;
import microservice.ecommerce.basketservice.business.dto.responses.GetAllBasketsResponse;
import microservice.ecommerce.basketservice.business.dto.responses.GetBasketResponse;
import microservice.ecommerce.basketservice.business.rules.BasketBusinessRules;
import microservice.ecommerce.basketservice.entities.Basket;
import microservice.ecommerce.basketservice.repository.BasketRepository;
import microservice.ecommerce.commonpackage.dto.CreateSalePaymentRequest;
import microservice.ecommerce.commonpackage.dto.basket.BasketTotalPriceResponse;
import microservice.ecommerce.commonpackage.dto.basket.KafkaBasketResponse;
import microservice.ecommerce.commonpackage.events.basket.BasketCreatedEvent;
import microservice.ecommerce.commonpackage.utils.dto.ClientResponse;
import microservice.ecommerce.commonpackage.utils.exceptions.BusinessException;
import microservice.ecommerce.commonpackage.utils.mappers.ModelMapperService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BasketManager implements BasketService {
    private final ModelMapperService mapper;
    private final BasketRepository repository;
    private final BasketBusinessRules rules;

    @Override
    public List<GetAllBasketsResponse> getAll() {
        List<Basket> baskets = repository.findAll();

        List<GetAllBasketsResponse> responses = baskets.stream()
                .map(basket -> mapper.forResponse().map(basket, GetAllBasketsResponse.class)).toList();

        return responses;
    }

    @Override
    public GetBasketResponse getById(String id) {
        Basket basket = repository.findById(id).orElseThrow();

        GetBasketResponse response = mapper.forResponse().map(basket, GetBasketResponse.class);

        return response;
    }

    @Override
    public KafkaBasketResponse add(BasketCreatedEvent event) {
        Basket basket = mapper.forRequest().map(event, Basket.class);

        basket.setId(null);
        repository.save(basket);

        return null;
    }

    @Override
    public ClientResponse controlBasketService() {
        ClientResponse response = new ClientResponse();

        try { response.setSuccess(true); }
        catch (BusinessException exception){
            response.setSuccess(false);
            response.setMessage(exception.getMessage());

        }
        return response;
    }

    @Override
    public void delete(String id) { repository.deleteById(id); }

    @Override
    public void deleteAll() { repository.deleteAll(); }

    @Override
    public BasketTotalPriceResponse basketTotalPrice (){
        List<Basket> baskets = repository.findAll();
        BasketTotalPriceResponse response = new BasketTotalPriceResponse();

        for (Basket total : baskets)
        { response.setTotalPrice(response.getTotalPrice() + total.getTotalPrice()); }

        return response;
    }

    @Override
    public KafkaBasketResponse buyCart(CreateSalePaymentRequest request) {
        BasketTotalPriceResponse basketTotalPrice = basketTotalPrice();

        request.setPrice(basketTotalPrice().getTotalPrice());
        rules.ensurePaymentIsValid(request);

        repository.deleteAll();

        return null;
    }
}