package Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {

    private int hotel_id;
    private String hotelName;
    private String address;
    private int numberOfRooms;
    private ArrayList<Room> rooms;

    public Hotel(String hotelName, String address, int numberOfRooms) {
        this.hotelName = hotelName;
        this.address = address;
        this.numberOfRooms = numberOfRooms;
        rooms=new ArrayList<>();
    }
    public Hotel(int hotel_id,String hotelName, String address, int numberOfRooms) {
        this.hotel_id=hotel_id;
        this.hotelName = hotelName;
        this.address = address;
        this.numberOfRooms = numberOfRooms;
        rooms=new ArrayList<>();
    }
}
