package com.org.proddaturiMinApp.controller;

import com.org.proddaturiMinApp.model.Product;
import com.org.proddaturiMinApp.repository.ProductRepository;
import com.org.proddaturiMinApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    ProductService productService;
    @GetMapping("/hello")
    public String hello() {
        return "welcome to product page";
    }

    @PostMapping("/add")
    public String addProduct(@RequestBody Product product) {
        if(productRepository.existsByproductName(product.getProductName())){
            return "Product already exists";
        }
         productRepository.save(product);
        return "Product added successfully";
    }

    @GetMapping("/getAllProducts")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/fetchById")
    public Optional<Product> getProductById(@RequestParam("productId") String id) {
        String pid = id.toLowerCase().trim();
        return productRepository.findById(pid);
    }
    @GetMapping("/fetchByprice")
    public List<Product> getProductByPrice(@RequestParam("price") double price) {
        return productRepository.findByPrice(price);
    }
    @GetMapping("/fetchByName")
    public List<Product> getProductByName(@RequestParam("productName") String name) {
        List<Product> filteredNames=productService.getProductByName(name.trim().toLowerCase());
        if (filteredNames.isEmpty()) {
            throw new RuntimeException("Products not found");
        }
        return filteredNames;
    }
    @PutMapping("/update")
    public Product updateProduct(@RequestParam("productId") String id, @RequestBody Product updatedProduct) {
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

    @DeleteMapping("/deleteById")
    public String deleteProduct(@RequestParam("productId") String id) {
        productRepository.deleteById(id);
        return "Product deleted successfully";
    }

    @DeleteMapping("/deleteByName")
    public String deleteByName(@RequestParam("productName") String name){
        productRepository.deleteByProductName(name);
        return "Product deleted successfully";
    }
}
