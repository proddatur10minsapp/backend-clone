package com.org.proddaturiMinApp.controller;
import com.org.proddaturiMinApp.model.ProductCategory;
import com.org.proddaturiMinApp.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/products/category")

public class ProductCategoryController {
    @Autowired
    ProductCategoryRepository productCategoryRepository;
    @PostMapping("/addCategory")
    public String addCategory(@RequestBody ProductCategory category)
    {
        productCategoryRepository.save(category);
        return "dataSaved";
    }

    @GetMapping("/getCategoryById/{id}")
    public Optional<ProductCategory>getCategoryById(@PathVariable String id)
    {
        return productCategoryRepository.findById(id);
    }

    @GetMapping("/getCategoryByName/{name}")
    public List<ProductCategory> getCategoryByName(@PathVariable String name)
    {
        System.out.println("text "+productCategoryRepository.findByCategoryName(name));
        return productCategoryRepository.findByCategoryName(name);
    }




}
