package com.gr8fulnez.HotelApi.dto;

import com.gr8fulnez.HotelApi.enums.UserRole;
import lombok.Data;

@Data
public class AuthenticationResponse {

    private String jwt;
    private String userId;
    private String userRole;
}
