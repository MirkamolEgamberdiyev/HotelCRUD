package uz.fido.hotelcrud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.fido.hotelcrud.entity.Hotel;
import uz.fido.hotelcrud.entity.Room;
import uz.fido.hotelcrud.payload.RoomDto;
import uz.fido.hotelcrud.repository.HotelRepository;
import uz.fido.hotelcrud.repository.RoomRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    RoomRepository roomRepository;
    @Autowired
    HotelRepository hotelRepository;

    public List<Room> getRooms() {
        return roomRepository.findAll();
    }

    public Room getRoom(Long id) {
        Optional<Room> roomOptional = roomRepository.findById(id);
        if (!roomOptional.isPresent()) return new Room();
        Room room = roomOptional.get();
        return room;
    }

    public Room createRoom(RoomDto roomDto) {
        Room room = new Room();
        room.setSize(roomDto.getSize());
        room.setNumber(roomDto.getNumber());
        room.setFloor(roomDto.getFloor());
        Optional<Hotel> hotelOptional = hotelRepository.findById(roomDto.getHotel_id());
        if (!hotelOptional.isPresent()) return new Room();
        Hotel hotel = hotelOptional.get();
        room.setHotel(hotel);
        Room save = roomRepository.save(room);
        return save;
    }

    public String updateRoom(Long id, RoomDto roomDto) {
        Optional<Room> roomOptional = roomRepository.findById(id);
        if (!roomOptional.isPresent()) return "Room not found";
        Room room = roomOptional.get();
        room.setSize(roomDto.getSize());
        room.setNumber(roomDto.getNumber());
        room.setFloor(roomDto.getFloor());
        Optional<Hotel> hotelOptional = hotelRepository.findById(roomDto.getHotel_id());
        if (!hotelOptional.isPresent()) return "Hotel not found";
        Hotel hotel = hotelOptional.get();
        room.setHotel(hotel);
        roomRepository.save(room);
        return "Room edited";
    }


    public String deleteRoom(Long id) {
        roomRepository.deleteById(id);
        return "deleted room";
    }
}
