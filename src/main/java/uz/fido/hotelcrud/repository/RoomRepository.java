package uz.fido.hotelcrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.fido.hotelcrud.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    boolean existsByNumber(Integer number);

}
