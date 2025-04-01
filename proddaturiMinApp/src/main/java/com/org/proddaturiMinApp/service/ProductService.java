package com.org.proddaturiMinApp.service;

import com.org.proddaturiMinApp.model.Category;
import com.org.proddaturiMinApp.model.Product;
import com.org.proddaturiMinApp.repository.CategoryRepository;
import com.org.proddaturiMinApp.repository.ProductRepository;
import com.org.proddaturiMinApp.utils.commonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class ProductService implements ProductServiceInterface {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Product> getFilteredProducts(String categoryName, int i) {
        List<Product> filteredProducts = new ArrayList<>();
        List<Product> allProducts = productRepository.findAll();

        int loopLimit = Math.min(commonConstants.range, allProducts.size());
            while(i <= loopLimit){
            String productCategory = getCategoryNameById(allProducts.get(i).getCategory());
            if (productCategory.equals(categoryName)) filteredProducts.add(allProducts.get(i));
            i++;
        }
        return filteredProducts;
    }

    public List<Product> getProducts(String categoryName) {
        return getFilteredProducts(categoryName, 0);
    }

    public List<Product> getProductsViaNextValue(String categoryName, int i) {
        return getFilteredProducts(categoryName, i);
    }


    public List<Product> allProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductsById(String id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product products) {
        return productRepository.save(products);
    }

    public List<Product> saveAllProduct(List<Product> products) {
        return productRepository.saveAll(products);
    }

    public Product updateProduct(String id, Product updatedProduct) {
        String productName = updatedProduct.getName();
        String productImage = updatedProduct.getImage();
        int price = updatedProduct.getPrice();
        int stock = updatedProduct.getQuantity();
        return productRepository.findById(id).map(product -> {
            if (productName != null) product.setName(productName);
            if (productImage != null) product.setImage(productImage);
            if (price != 0.0) product.setPrice(price);
            if (stock != 0) product.setQuantity(stock);
            return productRepository.save(product);
        }).orElseThrow(() -> new RuntimeException(commonConstants.productNotFound + "with id" + id));
    }

    public String deleteProductById(String id) {
        productRepository.deleteById(id);
        return commonConstants.productDeleted;
    }

    public Category getCategory(Product product) {
        return categoryRepository.findById(product.getCategory()).orElse(null);
    }

    public String getCategoryNameById(String categoryId) {
        List<Category> allCategories = categoryRepository.findAll();

        for (Category category : allCategories) {
            if (Objects.equals(category.getName(), categoryId)) {
                return category.getId();
            }
        }
        return commonConstants.categoryNotFound+" with id "+categoryId;
    }

}
