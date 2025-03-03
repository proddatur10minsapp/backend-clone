package com.org.proddaturiMinApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Product")
@TypeAlias("Product")
public class Product {
    @Id
    private String productId;
    private String productName;
    private String productType;
    private String productDescription;
    private String productImage;
    private double price;
    private int stock;
}
