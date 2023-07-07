package microservice.ecommerce.basketservice.business.rules;

import lombok.AllArgsConstructor;
import microservice.ecommerce.basketservice.api.client.payment.PaymentClient;
import microservice.ecommerce.commonpackage.dto.CreateSalePaymentRequest;
import microservice.ecommerce.commonpackage.utils.dto.ClientResponse;
import microservice.ecommerce.commonpackage.utils.exceptions.BusinessException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BasketBusinessRules {
    private final PaymentClient paymentClient;

    public void ensurePaymentIsValid(CreateSalePaymentRequest request){
        ClientResponse response = paymentClient.paymentValidation(request);

        if (!response.isSuccess()) throw new BusinessException(response.getMessage());
    }
}
