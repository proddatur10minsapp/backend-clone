package com.org.proddaturiMinApp.repository;

import com.org.proddaturiMinApp.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product,String> {
    List<Product> findByProductTypeContainingIgnoreCase(String category, Pageable pageable);
    List<Product> findByProductTypeContainingIgnoreCase(String category);
}
