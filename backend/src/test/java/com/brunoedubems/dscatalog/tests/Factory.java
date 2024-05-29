package com.brunoedubems.dscatalog.tests;

import java.time.Instant;

import com.brunoedubems.dscatalog.dto.ProductDTO;
import com.brunoedubems.dscatalog.entities.Category;
import com.brunoedubems.dscatalog.entities.Product;

public class Factory {
 
    public static Product createProduct(){
        Product product = new Product(1L,"The Lord of the Rings","Good Phone", 800.5,"https://img.com/img.png", Instant.parse("2020-10-20T03:00:00Z"));
        product.getCategories().add(new Category(2L,"Electronics"));
        return product;
    }

    public static ProductDTO createProductDTO(){
        Product product = createProduct();
        return new ProductDTO(product,product.getCategories());
    }
}
