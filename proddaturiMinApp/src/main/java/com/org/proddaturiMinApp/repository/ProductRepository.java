package com.org.proddaturiMinApp.repository;

import com.org.proddaturiMinApp.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product,String> {
}
