package microservice.ecommerce.basketservice.api.client.payment;

import lombok.extern.slf4j.Slf4j;
import microservice.ecommerce.commonpackage.dto.CreateSalePaymentRequest;
import microservice.ecommerce.commonpackage.utils.dto.ClientResponse;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PaymentClientFallback implements PaymentClient {
    @Override
    public ClientResponse paymentValidation(CreateSalePaymentRequest request) {
        log.info("PAYMENT SERVICE IS DOWN!");
        throw new RuntimeException("PAYMENT SERVICE IS DOWN!");
    }
}