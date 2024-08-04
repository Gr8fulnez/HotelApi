package com.gr8fulnez.HotelApi.services.customer.room;

import com.gr8fulnez.HotelApi.dto.RoomResponseDto;
import com.gr8fulnez.HotelApi.entity.Room;
import com.gr8fulnez.HotelApi.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerRoomServiceImpl implements CustomerRoomService {

    private final RoomRepository roomRepository;

    public RoomResponseDto getAvailableRooms(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber, 6);
        Page<Room> roomPage = roomRepository.findByAvailable(true, pageable);

        RoomResponseDto roomResponseDto = new RoomResponseDto();
        roomResponseDto.setPageNumber(roomPage.getPageable().getPageNumber());
        roomResponseDto.setTotalPages(roomPage.getTotalPages());
        roomResponseDto.setRoomDtoList(roomPage
                .stream().map(Room::getRoomDto).collect(Collectors.toList()));

        return roomResponseDto;
//        return null;

    }
}
