package microservice.ecommerce.stockservice.api.clients;

import io.github.resilience4j.retry.annotation.Retry;
import microservice.ecommerce.commonpackage.utils.dto.ClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "basket-service", fallback = BasketClientFallBack.class)
public interface BasketClient {
    @Retry(name = "basketValidation")
    @GetMapping(value = "/api/baskets/control-basket-service")
    ClientResponse addByIdForProduct();
}