package uz.fido.hotelcrud.payload;

import lombok.Data;

@Data
public class RoomDto {
    private Integer number;
    private Integer floor;
    private Integer size;
    private Integer hotel_id;
}
