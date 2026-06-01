package com.grocery.grocery_app.util;

import com.grocery.grocery_app.dto.*;
import com.grocery.grocery_app.model.*;
import java.util.List;
import java.util.stream.Collectors;

public class Mapper {

    public static ProductDTO toProductDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setStockQuantity(product.getStockQuantity());
        dto.setImageUrl(product.getImageUrl());
        dto.setCategoryName(product.getCategory().getName());
        dto.setCategoryId(product.getCategory().getId());
        return dto;
    }

    public static CartItemDTO toCartItemDTO(CartItem item) {
        CartItemDTO dto = new CartItemDTO();
        dto.setId(item.getId());
        dto.setProductId(item.getProduct().getId());
        dto.setProductName(item.getProduct().getName());
        dto.setProductImage(item.getProduct().getImageUrl());
        dto.setProductPrice(item.getProduct().getPrice());
        dto.setQuantity(item.getQuantity());
        dto.setSubtotal(item.getProduct().getPrice() * item.getQuantity());
        return dto;
    }

    public static CartDTO toCartDTO(Cart cart) {
        CartDTO dto = new CartDTO();
        dto.setId(cart.getId());
        List<CartItemDTO> items = cart.getItems() == null ? List.of() :
                cart.getItems().stream()
                .map(Mapper::toCartItemDTO)
                .collect(Collectors.toList());
        dto.setItems(items);
        dto.setTotalAmount(items.stream()
                .mapToDouble(CartItemDTO::getSubtotal).sum());
        return dto;
    }

    public static OrderItemDTO toOrderItemDTO(OrderItem item) {
        OrderItemDTO dto = new OrderItemDTO();
        dto.setId(item.getId());
        dto.setProductName(item.getProduct().getName());
        dto.setProductImage(item.getProduct().getImageUrl());
        dto.setQuantity(item.getQuantity());
        dto.setPriceAtPurchase(item.getPriceAtPurchase());
        dto.setSubtotal(item.getPriceAtPurchase() * item.getQuantity());
        return dto;
    }

    public static OrderDTO toOrderDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setStatus(order.getStatus().name());
        dto.setPaymentMode(order.getPaymentMode());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setCreatedAt(order.getCreatedAt());
        dto.setUserName(order.getUser().getName());
        dto.setDeliveryAddress(
                order.getAddress().getStreet() + ", " +
                        order.getAddress().getCity() + ", " +
                        order.getAddress().getState() + " - " +
                        order.getAddress().getPinCode()
        );
        dto.setOrderItems(order.getOrderItems().stream()
                .map(Mapper::toOrderItemDTO)
                .collect(Collectors.toList()));
        return dto;
    }

    public static AddressDTO toAddressDTO(Address address) {
        AddressDTO dto = new AddressDTO();
        dto.setId(address.getId());
        dto.setFullName(address.getFullName());
        dto.setMobile(address.getMobile());
        dto.setStreet(address.getStreet());
        dto.setCity(address.getCity());
        dto.setState(address.getState());
        dto.setPinCode(address.getPinCode());
        dto.setIsDefault(address.getIsDefault());
        return dto;
    }


}
