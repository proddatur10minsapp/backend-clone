package com.org.proddaturiMinApp.controller;

import com.org.proddaturiMinApp.model.Product;
import com.org.proddaturiMinApp.repository.ProductRepository;
import com.org.proddaturiMinApp.service.ProductService;
import com.org.proddaturiMinApp.utils.CommonProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users/products")
public class ProductUserController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;

    @GetMapping("/getProducts/{category}")
    public List<Product> getFirst20Products(@PathVariable("category") String category) {
        return productService.getFirst20ProductsViaCategory(category);
    }

    @GetMapping("/products/{id}")
    public Optional<Product> getProductById(@PathVariable String id) {
        return productRepository.findById(id);
    }

    @GetMapping("/getAllProducts/{category}")
    public List<Product> getAllProducts(@PathVariable("category") String category) {
        return productService.getAllProductsViaCategory(category);
    }

    @GetMapping("/getMoreProducts/{category}/{nextValues}")
    public ResponseEntity<?> getProductsViaCategoryNextValues(@PathVariable("category") String category,
                                                              @PathVariable("nextValues") int index) {
        List<Product> response = productService.getViaCategoryAndNextValue(category, index);

        if (response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(CommonProperties.productNotFound);
        }

        return ResponseEntity.ok(response);
    }

}
