package microservice.ecommerce.stockservice.api.clients;

import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import microservice.ecommerce.commonpackage.utils.dto.ClientResponse;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BasketClientFallBack implements BasketClient {
    @Override
    public ClientResponse addByIdForProduct() {
        log.info("BASKET SERVICE IS DOWN");
        throw new RuntimeException("BASKET SERVICE IS DOWN");
    }
}