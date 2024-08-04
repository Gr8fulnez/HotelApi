package com.gr8fulnez.HotelApi.services.customer.room;

import com.gr8fulnez.HotelApi.dto.RoomResponseDto;

public interface CustomerRoomService {
    RoomResponseDto getAvailableRooms(int pageNumber);
}
