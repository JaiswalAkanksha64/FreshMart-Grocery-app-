package com.grocery.grocery_app.dto;

import lombok.Data;

@Data
public class OrderItemDTO {

    private Long id;
    private String productName;
    private String productImage;
    private Integer quantity;
    private Double priceAtPurchase;
    private Double subtotal;
}
