package com.grocery.grocery_app.dto;

import lombok.Data;

@Data
public class AddressDTO {

    private Long id;
    private String fullName;
    private String mobile;
    private String street;
    private String city;
    private String state;
    private String pinCode;
    private Boolean isDefault;
}
