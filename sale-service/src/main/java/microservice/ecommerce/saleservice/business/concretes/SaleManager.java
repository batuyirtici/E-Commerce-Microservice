package microservice.ecommerce.saleservice.business.concretes;

import lombok.AllArgsConstructor;
import microservice.ecommerce.commonpackage.events.sale.SaleCreatedEvent;
import microservice.ecommerce.commonpackage.events.sale.SaleDeletedEvent;
import microservice.ecommerce.commonpackage.kafka.producer.KafkaProducer;
import microservice.ecommerce.commonpackage.utils.mappers.ModelMapperService;
import microservice.ecommerce.saleservice.business.abstracts.SaleService;
import microservice.ecommerce.saleservice.business.dto.requests.CreateSaleRequest;
import microservice.ecommerce.saleservice.business.dto.requests.UpdateSaleRequest;
import microservice.ecommerce.saleservice.business.dto.responses.CreateSaleResponse;
import microservice.ecommerce.saleservice.business.dto.responses.GetAllSalesResponse;
import microservice.ecommerce.saleservice.business.dto.responses.GetSaleResponse;
import microservice.ecommerce.saleservice.business.dto.responses.UpdateSaleResponse;
import microservice.ecommerce.saleservice.business.rules.SaleBusinessRules;
import microservice.ecommerce.saleservice.entities.Sale;
import microservice.ecommerce.saleservice.repository.SaleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class SaleManager implements SaleService {
    private final KafkaProducer producer;
    private final SaleBusinessRules rules;
    private final ModelMapperService mapper;
    private final SaleRepository repository;

    @Override
    public List<GetAllSalesResponse> getAll() {
        List<Sale> sales = repository.findAll();

        List<GetAllSalesResponse> responses = sales
                .stream()
                .map(sale -> mapper.forResponse().map(sale, GetAllSalesResponse.class))
                .toList();

        return responses;
    }

    @Override
    public GetSaleResponse getById(UUID id) {
        rules.checkIfSaleExists(id);

        Sale sale = repository.findById(id).orElseThrow();

        GetSaleResponse response = mapper.forResponse().map(sale, GetSaleResponse.class);

        return response;
    }

    @Override
    public CreateSaleResponse add(CreateSaleRequest request) {
        Sale sale = mapper.forRequest().map(request, Sale.class);

        sale.setId(UUID.randomUUID());
        sale.setTotalPrice(getTotalPrice(sale));
        sale.setSaleTime(LocalDateTime.now());

        repository.save(sale);

        sendKafkaSaleCreatedMessage(request.getProductId()); // Sale Step

        CreateSaleResponse response = mapper.forResponse().map(sale, CreateSaleResponse.class);

        return response;
    }

    @Override
    public UpdateSaleResponse update(UUID id, UpdateSaleRequest request) {
        rules.checkIfSaleExists(id);

        Sale sale = mapper.forRequest().map(request, Sale.class);

        sale.setId(id);
        repository.save(sale);

        UpdateSaleResponse response = mapper.forResponse().map(sale, UpdateSaleResponse.class);

        return response;
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfSaleExists(id);
        sendKafkaSaleDeletedMessage(id);
        repository.deleteById(id);
    }

//  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    private double getTotalPrice(Sale sale)
    { return sale.getPrice() * sale.getQuantity(); }

    private void sendKafkaSaleCreatedMessage(UUID productId)
    { producer.sendMessage(new SaleCreatedEvent(productId), "sale-created"); }

    private void sendKafkaSaleDeletedMessage(UUID id){
        UUID productId = repository.findById(id).orElseThrow().getProductId();
        producer.sendMessage(new SaleDeletedEvent(productId), "sale-deleted");
    }
}
