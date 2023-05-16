package microservice.ecommerce.stockservice.business.concretes;

import lombok.AllArgsConstructor;
import microservice.ecommerce.stockservice.business.abstracts.ProductService;
import microservice.ecommerce.stockservice.business.dto.requests.creates.CreateProductRequest;
import microservice.ecommerce.stockservice.business.dto.requests.updates.UpdateProductRequest;
import microservice.ecommerce.stockservice.business.dto.responses.creates.CreateProductResponse;
import microservice.ecommerce.stockservice.business.dto.responses.gets.GetAllProductsResponse;
import microservice.ecommerce.stockservice.business.dto.responses.gets.GetProductResponse;
import microservice.ecommerce.stockservice.business.dto.responses.updates.UpdateProductResponse;
import microservice.ecommerce.stockservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductManager implements ProductService {
    private final ProductRepository repository;

    @Override
    public List<GetAllProductsResponse> getAll() {
        return null;
    }

    @Override
    public GetProductResponse getById(UUID id) {
        return null;
    }

    @Override
    public CreateProductResponse add(CreateProductRequest request) {
        return null;
    }

    @Override
    public UpdateProductResponse update(UUID id, UpdateProductRequest request) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }
}
