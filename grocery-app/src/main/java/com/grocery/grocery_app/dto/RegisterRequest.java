package com.grocery.grocery_app.dto;

import lombok.Data;

@Data
public class RegisterRequest {

    private String name;
    private String email;
    private String password;
    private String mobileNumber;
}
