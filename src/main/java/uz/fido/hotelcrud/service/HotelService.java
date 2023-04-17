package uz.fido.hotelcrud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.fido.hotelcrud.entity.Hotel;
import uz.fido.hotelcrud.repository.HotelRepository;
import uz.fido.hotelcrud.responce.ApiResponse;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {
    @Autowired
    HotelRepository hotelRepository;

    public ApiResponse getAllHotel() {
        List<Hotel> hotels = hotelRepository.findAll();
        return new ApiResponse("Hotel List", true, hotels);
    }


    public ApiResponse addHotel(Hotel hotel) {
        if (!hotelRepository.existsByName(hotel.getName())) {
            Hotel save = hotelRepository.save(hotel);
            return new ApiResponse("Hotel added successfully", true, save);
        }
        return new ApiResponse("Hotel with this model already exist", false);

    }

    public ApiResponse deleteHotel(Integer id){
        if (hotelRepository.existsById(id)) {
            hotelRepository.deleteById(id);
            return new ApiResponse("Found and deleted",true);
        }
        return new ApiResponse("Hotel does not exist",false);
    }


    public ApiResponse editHotel(Integer id,Hotel hotel) {
        if (hotelRepository.existsById(id)) {
            Optional<Hotel> byId = hotelRepository.findById(id);
            Hotel hotel1 = byId.get();
            hotel1.setName(hotel.getName());
            Hotel save = hotelRepository.save(hotel1);
            return new ApiResponse("Found and updated",true,save);
        }
        return new ApiResponse("Hotel does not exist",false);
    }





}
