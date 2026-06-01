package com.grocery.grocery_app.service;

import com.grocery.grocery_app.model.Address;
import com.grocery.grocery_app.model.User;
import com.grocery.grocery_app.repository.AddressRepository;
import com.grocery.grocery_app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public List<Address> getAddresses(Long userId) {
        return addressRepository.findByUserId(userId);
    }

    public Address addAddress(Long userId, Address address) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found!"));
        address.setUser(user);

        if (address.getIsDefault()) {
            addressRepository.findByUserIdAndIsDefaultTrue(userId)
                    .ifPresent(existing -> {
                        existing.setIsDefault(false);
                        addressRepository.save(existing);
                    });
        }
        return addressRepository.save(address);
    }

    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }
}
