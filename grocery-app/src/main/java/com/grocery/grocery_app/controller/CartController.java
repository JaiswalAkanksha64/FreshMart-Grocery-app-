package com.grocery.grocery_app.controller;

import com.grocery.grocery_app.dto.ApiResponse;
import com.grocery.grocery_app.dto.CartDTO;
import com.grocery.grocery_app.model.Cart;
import com.grocery.grocery_app.model.User;
import com.grocery.grocery_app.repository.UserRepository;
import com.grocery.grocery_app.service.CartService;
import com.grocery.grocery_app.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class CartController {

    private final CartService cartService;
    private final UserRepository userRepository;

    private Long getUserId(UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found!"));
        return user.getId();
    }

    @GetMapping
    public ResponseEntity<ApiResponse<CartDTO>> getCart(
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(ApiResponse.success("Cart fetched",
                Mapper.toCartDTO(cartService.getCart(getUserId(userDetails)))));
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<CartDTO>> addToCart(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam Long productId,
            @RequestParam Integer quantity) {
        return ResponseEntity.ok(ApiResponse.success("Added to cart",
                Mapper.toCartDTO(cartService.addToCart(
                        getUserId(userDetails), productId, quantity))));
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<CartDTO>> updateCart(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam Long cartItemId,
            @RequestParam Integer quantity) {
        return ResponseEntity.ok(ApiResponse.success("Cart updated",
                Mapper.toCartDTO(cartService.updateCartItem(
                        getUserId(userDetails), cartItemId, quantity))));
    }

    @DeleteMapping("/remove/{cartItemId}")
    public ResponseEntity<ApiResponse<CartDTO>> removeFromCart(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long cartItemId) {
        return ResponseEntity.ok(ApiResponse.success("Item removed",
                Mapper.toCartDTO(cartService.removeFromCart(
                        getUserId(userDetails), cartItemId))));
    }
}

