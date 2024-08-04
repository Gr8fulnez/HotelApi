package com.gr8fulnez.HotelApi.repository;

import com.gr8fulnez.HotelApi.entity.User;
import com.gr8fulnez.HotelApi.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findFirstByEmail(String email);

    Optional<User> findByUserRole(UserRole userRole);

}
