package com.org.proddaturiMinApp.repository;

import com.org.proddaturiMinApp.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product,String> {
    boolean existsByproductName(String name);
    List<Product> findByPrice(double price);
    @Transactional
    @Query(value = "{ 'productName': ?0 }", delete = true)
    void deleteByProductName(String productName);
}
