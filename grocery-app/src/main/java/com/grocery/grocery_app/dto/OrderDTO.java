package com.grocery.grocery_app.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTO {

    private Long id;
    private String status;
    private String paymentMode;
    private Double totalAmount;
    private LocalDateTime createdAt;
    private List<OrderItemDTO> orderItems;
    private String deliveryAddress;
    private String userName;

}
