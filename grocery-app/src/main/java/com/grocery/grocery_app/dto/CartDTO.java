package com.grocery.grocery_app.dto;

import lombok.Data;

import java.util.List;

@Data
public class CartDTO {

    private Long id;
    private List<CartItemDTO> items;
    private Double totalAmount;
}
