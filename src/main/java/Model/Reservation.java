package Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Reservation {

    private int reservation_id;
    private LocalDate dateStart; //change to LocalDate
    private LocalDate dateEnd;   //change to LocalDate
    private int person_id;
    private int hotel_id;
    private int room_id;

    public Reservation(LocalDate dateStart, LocalDate dateEnd, int person_id, int hotel_id, int room_id) {
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.person_id = person_id;
        this.hotel_id = hotel_id;
        this.room_id = room_id;
    }

    public Reservation(int reservation_id,String dateStart, String dateEnd, int person_id, int hotel_id, int room_id) {
        this.reservation_id=reservation_id;
        this.dateStart =LocalDate.of(Integer.parseInt(dateStart.split("-")[0]),Integer.parseInt(dateStart.split("-")[1]),Integer.parseInt(dateStart.split("-")[2]));
        this.dateEnd = LocalDate.of(Integer.parseInt(dateEnd.split("-")[0]),Integer.parseInt(dateEnd.split("-")[1]),Integer.parseInt(dateEnd.split("-")[2]));
        this.person_id = person_id;
        this.hotel_id = hotel_id;
        this.room_id = room_id;
    }

}
