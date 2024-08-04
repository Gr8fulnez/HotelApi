package com.gr8fulnez.HotelApi.dto;

import lombok.Data;

@Data
public class SignupRequest {

    private String email;
    private  String password;
    private  String name;
}
