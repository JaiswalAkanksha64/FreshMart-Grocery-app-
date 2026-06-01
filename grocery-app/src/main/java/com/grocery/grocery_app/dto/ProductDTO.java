package com.grocery.grocery_app.dto;

import lombok.Data;

@Data
public class ProductDTO {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer stockQuantity;
    private String imageUrl;
    private String categoryName;
    private Long categoryId;

}
