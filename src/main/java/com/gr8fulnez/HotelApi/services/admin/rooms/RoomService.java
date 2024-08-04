package com.gr8fulnez.HotelApi.services.admin.rooms;

import com.gr8fulnez.HotelApi.dto.RoomDto;
import com.gr8fulnez.HotelApi.dto.RoomResponseDto;

public interface RoomService {
    boolean postRoom(RoomDto roomDto);
    RoomResponseDto getAllRooms(int pageNumber);
    RoomDto getRoomById(Long id);
    boolean updateRoom(Long id, RoomDto roomDto);
    void deleteRoom(Long id);
}
