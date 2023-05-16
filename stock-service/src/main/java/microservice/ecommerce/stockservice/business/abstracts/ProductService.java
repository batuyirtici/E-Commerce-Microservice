package microservice.ecommerce.stockservice.business.abstracts;

import microservice.ecommerce.stockservice.business.dto.requests.creates.CreateProductRequest;
import microservice.ecommerce.stockservice.business.dto.requests.updates.UpdateProductRequest;
import microservice.ecommerce.stockservice.business.dto.responses.creates.CreateProductResponse;
import microservice.ecommerce.stockservice.business.dto.responses.gets.GetAllProductsResponse;
import microservice.ecommerce.stockservice.business.dto.responses.gets.GetProductResponse;
import microservice.ecommerce.stockservice.business.dto.responses.updates.UpdateProductResponse;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<GetAllProductsResponse> getAll();
    GetProductResponse getById(UUID id);
    CreateProductResponse add(CreateProductRequest request);
    UpdateProductResponse update (UUID id, UpdateProductRequest request);
    void delete (UUID id);
}