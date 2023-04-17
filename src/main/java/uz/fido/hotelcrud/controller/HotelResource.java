package uz.fido.hotelcrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.fido.hotelcrud.entity.Hotel;

import uz.fido.hotelcrud.repository.HotelRepository;
import uz.fido.hotelcrud.responce.ApiResponse;
import uz.fido.hotelcrud.service.HotelService;

@RestController
@RequestMapping("/hotel")
public class HotelResource {
    @Autowired
    HotelService hotelService;
    @Autowired
    HotelRepository hotelRepository;

    @GetMapping("/getHotels")
    public ApiResponse getAllHotel() {
        ApiResponse apiResponse = hotelService.getAllHotel();
        return apiResponse;
    }

    @GetMapping("/getHotels2")
    public Page<Hotel> getAllHotels(@RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Hotel> roomPage = hotelRepository.findAll(pageable);
        return roomPage;
    }

    @PostMapping("/addHotel")
    public ApiResponse addHotel(@RequestBody Hotel hotel) {
        ApiResponse apiResponse = hotelService.addHotel(hotel);
        return apiResponse;
    }

    @PutMapping("/update")
    public ApiResponse Update(@PathVariable Integer id, @RequestBody Hotel hotel){
        return hotelService.editHotel(id, hotel);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse delete(@PathVariable Integer id){
        return hotelService.deleteHotel(id);
    }

}
