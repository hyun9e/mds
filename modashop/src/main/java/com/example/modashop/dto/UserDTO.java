package com.example.modashop.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String fullName;
    private String address;
    private String phone;
    private boolean isAdmin;
}