package com.org.proddaturiMinApp.controller;

import com.org.proddaturiMinApp.model.Product;
import com.org.proddaturiMinApp.repository.ProductRepository;
import com.org.proddaturiMinApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/category")
public class ProductCategoryController {

    @Autowired
    ProductRepository productRepository;
@Autowired
    ProductService service;
    @GetMapping("/{type}")
    public List<Product> getProductByType(@PathVariable String type) {
        List<Product> filteredProducts = service.getProductsByType(type);
        if (filteredProducts.isEmpty()) {
            throw new RuntimeException("Category " + type + " not found");
        }
        return filteredProducts;
    }
}
