package com.gr8fulnez.HotelApi.repository;

import com.gr8fulnez.HotelApi.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
