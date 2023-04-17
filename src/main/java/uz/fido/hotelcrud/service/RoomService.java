package uz.fido.hotelcrud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.fido.hotelcrud.entity.Hotel;
import uz.fido.hotelcrud.entity.Room;
import uz.fido.hotelcrud.payload.RoomDto;
import uz.fido.hotelcrud.repository.HotelRepository;
import uz.fido.hotelcrud.repository.RoomRepository;
import uz.fido.hotelcrud.responce.ApiResponse;

import java.util.Optional;

@Service
public class RoomService {
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    HotelRepository hotelRepository;

    public Page<Room> getAllRoom(Pageable pageable) {
        Page<Room> roomPage = roomRepository.findAll(pageable);
        return roomPage;
    }

    public ApiResponse addRoom(RoomDto roomDto) {
        if (!roomRepository.existsByNumber(roomDto.getNumber())) {
            Room room = new Room();
            room.setFloor(roomDto.getFloor());
            room.setSize(roomDto.getSize());
            room.setNumber(roomDto.getNumber());
            Optional<Hotel> hotelOptional = hotelRepository.findById(roomDto.getHotel_id());
            if (!hotelOptional.isPresent()) {
                return new ApiResponse("Room not found", false);
            }
            Hotel hotel = hotelOptional.get();
            room.setHotel(hotel);
            Room save = roomRepository.save(room);
            return new ApiResponse("Room added successfully", true, save);
        }
        return new ApiResponse("Room with this model already exist", false);
    }

    public ApiResponse editRoom(Integer id, RoomDto roomDto) {
        if (roomRepository.existsById(id)) {
            Optional<Room> byId = roomRepository.findById(id);
            Room room = byId.get();
            room.setSize(roomDto.getSize());
            room.setNumber(roomDto.getNumber());
            room.setFloor(roomDto.getFloor());

            Optional<Hotel> byId1 = hotelRepository.findById(roomDto.getHotel_id());
            Hotel hotel = byId1.get();
            room.setHotel(hotel);
            Room save = roomRepository.save(room);
            return new ApiResponse("found and updated", true, save);
        }
        return new ApiResponse("factory does not exist", false);
    }

    public ApiResponse delete(Integer id){
        if (roomRepository.existsById(id)) {
            roomRepository.deleteById(id);
            return new ApiResponse("Found and deleted",true);
        }
        return new ApiResponse("Room does not exist",false);
    }



}
