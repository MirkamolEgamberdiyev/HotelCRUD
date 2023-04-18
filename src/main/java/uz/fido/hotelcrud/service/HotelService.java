package uz.fido.hotelcrud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.fido.hotelcrud.entity.Hotel;
import uz.fido.hotelcrud.entity.Room;
import uz.fido.hotelcrud.repository.HotelRepository;
import uz.fido.hotelcrud.repository.RoomRepository;

import java.util.List;

@Service
public class HotelService {
    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    RoomRepository roomRepository;

    public List<Hotel> findAllHotels() {
        return hotelRepository.findAll();
    }

    public Hotel findHotelById(Long id) {
        return hotelRepository.findById(id).get();
    }

    public Hotel saveHotel(Hotel hotel) {
        for (Room room : hotel.getRooms()) {
            if (!roomRepository.existsByNumber(room.getNumber()))
                roomRepository.save(room);
        }

        return hotelRepository.save(hotel);
    }

    public void deleteHotelById(Long id) {
        hotelRepository.deleteById(id);
    }

    public Page<Room> findRoomsByHotelId(Long hotelId, Pageable pageable) {
        return hotelRepository.findRoomsByHotelId(hotelId, pageable);
    }

}
