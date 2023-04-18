package uz.fido.hotelcrud.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.fido.hotelcrud.entity.Hotel;
import uz.fido.hotelcrud.entity.Room;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    boolean existsByName(String name);

    @Query("SELECT r FROM Room r WHERE r.hotel.id = :hotelId")
    Page<Room> findRoomsByHotelId(Long hotelId, Pageable pageable);

}
