package com.org.proddaturiMinApp.service;

import com.org.proddaturiMinApp.model.Product;
import com.org.proddaturiMinApp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    ProductRepository repository;

    public List<Product> getFirst20ProductsViaCategory(String category) {
        return repository.findByProductTypeContainingIgnoreCase(category.trim(), PageRequest.of(0, 10));
    }

    public List<Product> getAllProductsViaCategory(String category) {
        return repository.findByProductTypeContainingIgnoreCase(category.trim());
    }

    public List<Product> getViaCategoryAndNextValue(String category, int nextValue) {
        Pageable pageable = PageRequest.of(nextValue / 10, 10);  // Convert index to page number
        return repository.findByProductTypeContainingIgnoreCase(category.trim(), pageable);
    }


}


