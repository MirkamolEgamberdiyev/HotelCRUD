package uz.fido.hotelcrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.fido.hotelcrud.entity.Room;
import uz.fido.hotelcrud.payload.RoomDto;
import uz.fido.hotelcrud.service.RoomService;
import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomResource {

    @Autowired
    RoomService roomService;

    @GetMapping("/getAllRooms")
    public List<Room> getAllRooms() {
        return roomService.getRooms();
    }

    @GetMapping("/getRoom/{id}")
    public Room getRoom(@PathVariable Long id) {
        return roomService.getRoom(id);
    }

    @PostMapping("/create")
    public Room createRoom(@RequestBody RoomDto roomDto) {
        return roomService.createRoom(roomDto);
    }

    @PutMapping("/update/{id}")
    public String updateRoom(@PathVariable Long id, @RequestBody RoomDto roomDto) {
        return roomService.updateRoom(id, roomDto);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteRoom(@PathVariable Long id) {
        return roomService.deleteRoom(id);
    }

}
