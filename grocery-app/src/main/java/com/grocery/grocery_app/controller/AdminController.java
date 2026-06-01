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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

    private final OrderService orderService;
    private final UserRepository userRepository;

    @GetMapping("/orders")
    public ResponseEntity<ApiResponse<List<OrderDTO>>> getAllOrders() {
        List<OrderDTO> orders = orderService.getAllOrders()
                .stream().map(Mapper::toOrderDTO).collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success("Orders fetched", orders));
    }

    @PutMapping("/orders/{id}/status")
    public ResponseEntity<ApiResponse<OrderDTO>> updateStatus(
            @PathVariable Long id,
            @RequestParam Order.OrderStatus status) {
        return ResponseEntity.ok(ApiResponse.success("Status updated",
                Mapper.toOrderDTO(orderService.updateOrderStatus(id, status))));
    }

    @GetMapping("/users")
    public ResponseEntity<ApiResponse<List<User>>> getAllUsers() {
        return ResponseEntity.ok(ApiResponse.success("Users fetched",
                userRepository.findAll()));
    }

    @GetMapping("/dashboard/stats")
    public ResponseEntity<ApiResponse<?>> getDashboardStats() {
        long totalOrders = orderService.getAllOrders().size();
        long totalUsers = userRepository.count();
        return ResponseEntity.ok(ApiResponse.success("Stats fetched",
                java.util.Map.of(
                        "totalOrders", totalOrders,
                        "totalUsers", totalUsers
                )));
    }
}
