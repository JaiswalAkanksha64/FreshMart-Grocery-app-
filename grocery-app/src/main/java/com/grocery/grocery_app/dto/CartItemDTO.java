package com.grocery.grocery_app.dto;

import lombok.Data;

@Data
public class CartItemDTO {

    private Long id;
    private Long productId;
    private String productName;
    private String productImage;
    private Double productPrice;
    private Integer quantity;
    private Double subtotal;
}
