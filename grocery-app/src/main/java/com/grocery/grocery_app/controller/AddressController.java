package com.grocery.grocery_app.controller;

import com.grocery.grocery_app.dto.AddressDTO;
import com.grocery.grocery_app.dto.ApiResponse;
import com.grocery.grocery_app.model.Address;
import com.grocery.grocery_app.model.User;
import com.grocery.grocery_app.repository.UserRepository;
import com.grocery.grocery_app.service.AddressService;
import com.grocery.grocery_app.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/address")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AddressController {

    private final AddressService addressService;
    private final UserRepository userRepository;

    private Long getUserId(UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found!"));
        return user.getId();
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<AddressDTO>>> getAddresses(
            @AuthenticationPrincipal UserDetails userDetails) {
        List<AddressDTO> addresses = addressService.getAddresses(getUserId(userDetails))
                .stream().map(Mapper::toAddressDTO).collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success("Addresses fetched", addresses));
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<AddressDTO>> addAddress(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody Address address) {
        return ResponseEntity.ok(ApiResponse.success("Address added",
                Mapper.toAddressDTO(addressService.addAddress(
                        getUserId(userDetails), address))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
        return ResponseEntity.ok(ApiResponse.success("Address deleted", null));
    }
}
