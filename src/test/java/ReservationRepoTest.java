import Model.Reservation;
import Repository.ReservationRepo;
import org.junit.Test;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ReservationRepoTest {

    @Test
    public void addReservationTest(){
        ReservationRepo repo=new ReservationRepo();

        LocalDate dateStart=LocalDate.of(2020,5,20);
        LocalDate dateEnd=LocalDate.of(2020,5,22);

        Reservation reservation=new Reservation(dateStart,dateEnd,1,45,3);
        repo.insertReservation(reservation);
    }
}
