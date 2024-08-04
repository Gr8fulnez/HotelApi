package com.gr8fulnez.HotelApi.services.customer.booking;

import com.gr8fulnez.HotelApi.dto.ReservationDto;

public interface BookingService {
    boolean postReservation(ReservationDto reservationDto);
}
