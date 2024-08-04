package com.gr8fulnez.HotelApi.services.auth;

import com.gr8fulnez.HotelApi.dto.SignupRequest;
import com.gr8fulnez.HotelApi.dto.UserDto;
import com.gr8fulnez.HotelApi.entity.User;
import com.gr8fulnez.HotelApi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface AuthService {

    UserDto createUser(SignupRequest signupRequest);
}
