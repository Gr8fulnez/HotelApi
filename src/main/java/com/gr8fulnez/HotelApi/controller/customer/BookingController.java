package com.gr8fulnez.HotelApi.controller.customer;

import com.gr8fulnez.HotelApi.dto.ReservationDto;
import com.gr8fulnez.HotelApi.services.customer.booking.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/book")
    public ResponseEntity<?> PostBooking(@RequestBody ReservationDto reservationDto){
        boolean success = bookingService.postReservation(reservationDto);

        if(success){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
