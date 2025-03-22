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
@Document(collection = "ProductCategory")
@TypeAlias("ProductCategory")
public class ProductCategory {
    @Id
    private String categoryId;
    private String categoryName;

}
