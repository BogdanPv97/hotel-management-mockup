package Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    private int room_id;
    private int numberOfPeople;
    private double price;
    private boolean available;
    private int hotel_id;



    public Room(int room_id,int numberOfPeople, double price, boolean available) {
        this.room_id=room_id;
        this.numberOfPeople = numberOfPeople;
        this.price = price;
        this.available = available;

    }
}
