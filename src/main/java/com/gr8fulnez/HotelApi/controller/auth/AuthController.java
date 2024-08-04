package com.gr8fulnez.HotelApi.controller.auth;

import com.gr8fulnez.HotelApi.dto.AuthenticationRequest;
import com.gr8fulnez.HotelApi.dto.AuthenticationResponse;
import com.gr8fulnez.HotelApi.dto.SignupRequest;
import com.gr8fulnez.HotelApi.dto.UserDto;
import com.gr8fulnez.HotelApi.entity.User;
import com.gr8fulnez.HotelApi.repository.UserRepository;
import com.gr8fulnez.HotelApi.services.auth.AuthService;
import com.gr8fulnez.HotelApi.services.jwt.UserService;
import com.gr8fulnez.HotelApi.utils.JwtUtil;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    @PostMapping("/signup")
    private ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest){
        try {
            UserDto createdUser = authService.createUser(signupRequest);
            return new ResponseEntity<>(createdUser, HttpStatus.OK);
        }catch (EntityExistsException entityExistsException){
            return new ResponseEntity<>("User already exists", HttpStatus.NOT_ACCEPTABLE);
        }catch (Exception e){
            return new ResponseEntity<>("User not created, come again later", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    private AuthenticationResponse createAuthenticationToken(
            @RequestBody AuthenticationRequest authenticationRequest){
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
                            authenticationRequest.getPassword()));
        }catch (BadCredentialsException e){
            throw new BadCredentialsException("Incorrect username or password.");
        }
        final UserDetails userDetails = userService.userDetailsService()
                .loadUserByUsername(authenticationRequest.getEmail());
//        Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());
        Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails);

        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        if(optionalUser.isPresent()){
            authenticationResponse.setJwt(jwt);
            authenticationResponse.setUserRole(String.valueOf(optionalUser.get().getUserRole()));
            authenticationResponse.setUserId(String.valueOf(optionalUser.get().getId()));
        }
        return authenticationResponse;
    }
}
