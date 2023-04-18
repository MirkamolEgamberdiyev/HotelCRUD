package uz.fido.hotelcrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uz.fido.hotelcrud.entity.Hotel;
import uz.fido.hotelcrud.entity.Room;
import uz.fido.hotelcrud.service.HotelService;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelResource {
    @Autowired
    HotelService hotelService;

    @GetMapping("/getAllHotels")
    public List<Hotel> findAllHotels() {
        return hotelService.findAllHotels();
    }

    @GetMapping("/getHotel/{id}")
    public Hotel findHotelById(@PathVariable Long id) {
        return hotelService.findHotelById(id);
    }


    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Hotel saveHotel(@RequestBody Hotel hotel) {
        return hotelService.saveHotel(hotel);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteHotelById(@PathVariable Long id) {
        hotelService.deleteHotelById(id);
    }

    @GetMapping("/{id}/rooms")
    public Page<Room> findRoomsByHotelId(@PathVariable Long id, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return hotelService.findRoomsByHotelId(id, PageRequest.of(page, size));
    }

}
