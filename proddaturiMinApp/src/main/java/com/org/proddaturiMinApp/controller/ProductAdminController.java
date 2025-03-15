package com.org.proddaturiMinApp.controller;

import com.org.proddaturiMinApp.model.Product;
import com.org.proddaturiMinApp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/products")
public class ProductAdminController {
    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }
    @PostMapping("/addMultiple")
    public List<Product> addMultipleProducts(@RequestBody List<Product> products) {
        return productRepository.saveAll(products);
    }

    @GetMapping("/getAllProducts")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @PutMapping("/update/{id}")
    public Product updateProduct(@PathVariable String id, @RequestBody Product updatedProduct) {
        String productName = updatedProduct.getProductName();
        String productType = updatedProduct.getProductType();
        String productDescription = updatedProduct.getProductDescription();
        String productImage = updatedProduct.getProductImage();
        double price = updatedProduct.getPrice();
        int stock = updatedProduct.getStock();
        return productRepository.findById(id).map(product -> {
            if (productName != null) product.setProductName(productName);
            if (productType != null) product.setProductType(productType);
            if (productDescription != null) product.setProductDescription(productDescription);
            if (productImage != null) product.setProductImage(productImage);
            if (price != 0.0) product.setPrice(price);
            if (stock != 0) product.setStock(stock);
            return productRepository.save(product);
        }).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable String id) {
        productRepository.deleteById(id);
        return "Product deleted successfully";
    }
}
