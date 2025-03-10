package com.org.proddaturiMinApp.controller;

import com.org.proddaturiMinApp.model.Product;
import com.org.proddaturiMinApp.repository.ProductRepository;
import com.org.proddaturiMinApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/category")
public class ProductCategoryController {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductService service;

    //Get all the Products By ProductType code
    @GetMapping("/type")
    public List<Product> getProductByType(@RequestParam("productType") String type) {
        List<Product> filteredProducts = service.getProductsByType(type);
        if (filteredProducts.isEmpty()) {
            throw new RuntimeException("Category " + type + " not found");
        }
        return filteredProducts;
    }

    //Get all Categories code
    @GetMapping("/allCategories")
    public List<String> getAllCategories() {
        return service.getAllCategories();
    }
}