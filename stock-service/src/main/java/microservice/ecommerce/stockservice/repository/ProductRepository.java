package microservice.ecommerce.stockservice.repository;

import microservice.ecommerce.stockservice.entities.Product;
import microservice.ecommerce.stockservice.entities.enums.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID>
{ List<Product> findAllByStateIsNot(State state); }