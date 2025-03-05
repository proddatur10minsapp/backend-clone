package com.org.proddaturiMinApp.service;

import com.org.proddaturiMinApp.model.Product;
import com.org.proddaturiMinApp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ProductService {
    @Autowired
    ProductRepository repository;

    public List<Product>getProductByName(String name) {
        name =name.trim().toLowerCase();
        String[] splitProductNames=name.split(" ");
        List<Product> products = repository.findAll();
        List<Product> filteredProductNames = new ArrayList<>();
        for(String splitName:splitProductNames){
            for(Product names:products){
               String ProductName= names.getProductName().trim().toLowerCase();
               if(ProductName.contains(splitName)) {
                   if(!filteredProductNames.contains(names)) filteredProductNames.add(names);
               }
            }
        }
        return filteredProductNames;
    }

    public List<Product> getProductsByType(String type){
        List<Product> products=repository.findAll();
        type=type.trim().toLowerCase();
        List<Product> filteredProduct=new ArrayList<>();
        for(Product values:products){
            String proType =values.getProductType().trim().toLowerCase();
            if(proType.contains(type)){
              filteredProduct.add(values);
            }
        }
        return filteredProduct;
    }
}


