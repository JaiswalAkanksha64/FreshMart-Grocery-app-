package com.grocery.grocery_app.controller;

import com.grocery.grocery_app.dto.ApiResponse;
import com.grocery.grocery_app.dto.OrderDTO;
import com.grocery.grocery_app.model.Order;
import com.grocery.grocery_app.model.User;
import com.grocery.grocery_app.repository.UserRepository;
import com.grocery.grocery_app.service.OrderService;
import com.grocery.grocery_app.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

    private final OrderService orderService;
    private final UserRepository userRepository;

    private Long getUserId(UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found!"));
        return user.getId();
    }

    @PostMapping("/place")
    public ResponseEntity<ApiResponse<OrderDTO>> placeOrder(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam Long addressId) {
        return ResponseEntity.ok(ApiResponse.success("Order placed successfully!",
                Mapper.toOrderDTO(orderService.placeOrder(
                        getUserId(userDetails), addressId))));
    }

    @GetMapping("/my-orders")
    public ResponseEntity<ApiResponse<List<OrderDTO>>> getMyOrders(
            @AuthenticationPrincipal UserDetails userDetails) {
        List<OrderDTO> orders = orderService.getMyOrders(getUserId(userDetails))
                .stream().map(Mapper::toOrderDTO).collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success("Orders fetched", orders));
    }
}
