package com.org.proddaturiMinApp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.org.proddaturiMinApp.model.ProductCategory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ProductCategoryRepository extends MongoRepository<ProductCategory,String> {
  List<ProductCategory>findByCategoryName(String categoryName);
}
