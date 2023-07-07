package microservice.ecommerce.basketservice.repository;

import microservice.ecommerce.basketservice.entities.Basket;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BasketRepository extends MongoRepository<Basket, String>{}