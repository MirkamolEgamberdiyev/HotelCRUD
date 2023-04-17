package uz.fido.hotelcrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.fido.hotelcrud.entity.Room;

import uz.fido.hotelcrud.payload.RoomDto;
import uz.fido.hotelcrud.repository.RoomRepository;
import uz.fido.hotelcrud.responce.ApiResponse;
import uz.fido.hotelcrud.service.RoomService;

@RestController
@RequestMapping("/room")
public class RoomResource {
    @Autowired
    RoomService roomService;
    @Autowired
    RoomRepository roomRepository;

    @GetMapping("/getAllRoom")
    public Page<Room> getAllRoom(@RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Room> roomPage = roomRepository.findAll(pageable);
        return roomPage;
    }

    @PostMapping("/addRoom")
    public ApiResponse addRoom(@RequestBody RoomDto roomDto) {
        return roomService.addRoom(roomDto);
    }

    @PutMapping("/update/{id}")
    public ApiResponse updateRoom(@PathVariable Integer id, @RequestBody RoomDto roomDto) {
        return roomService.editRoom(id, roomDto);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse delete(@PathVariable Integer id){
        return roomService.delete(id);
    }



}
