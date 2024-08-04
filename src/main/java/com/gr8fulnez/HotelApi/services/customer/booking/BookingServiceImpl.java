package com.gr8fulnez.HotelApi.services.customer.booking;

import com.gr8fulnez.HotelApi.dto.ReservationDto;
import com.gr8fulnez.HotelApi.entity.Reservation;
import com.gr8fulnez.HotelApi.entity.Room;
import com.gr8fulnez.HotelApi.entity.User;
import com.gr8fulnez.HotelApi.enums.ReservationStatus;
import com.gr8fulnez.HotelApi.repository.ReservationRepository;
import com.gr8fulnez.HotelApi.repository.RoomRepository;
import com.gr8fulnez.HotelApi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl {

    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;

    public boolean postReservation(ReservationDto reservationDto) {
        Optional<User> optionalUser = userRepository.findById(reservationDto.getUserId());
        Optional<Room> optionalRoom = roomRepository.findById(reservationDto.getRoomId());

        if (optionalRoom.isPresent() && optionalUser.isPresent()) {
            Reservation reservation = new Reservation();

            reservation.setRoom(optionalRoom.get());
            reservation.setUser(optionalUser.get());
            reservation.setCheckinDate(reservationDto.getCheckinDate());
            reservation.setCheckOutDate(reservationDto.getCheckOutDate());
            reservation.setReservationStatus(ReservationStatus.PENDING);

            Long days = ChronoUnit.DAYS.between(reservationDto.getCheckinDate(), reservationDto.getCheckOutDate());

            reservation.setPrice(optionalRoom.get().getPrice() * days);

            reservationRepository.save(reservation);
            return true;

        }
        return false;
    }

}
