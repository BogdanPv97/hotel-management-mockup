package View;

import Controller.HotelController;
import Controller.PersonController;
import Controller.ReservationController;
import Controller.RoomController;
import Model.Reservation;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class ReservationView{

    Scanner keyboard;
    ReservationController reservationController;
    PersonController personController;
    HotelController hotelController;
    RoomController roomController;

    public ReservationView(){
        keyboard=new Scanner(System.in);
        personController=new PersonController();
        roomController=new RoomController();
        hotelController=new HotelController();
        reservationController=new ReservationController();
    }

    public void play(int room_id,int hotel_id, String username){
        System.out.println("Please enter start date: ");
        System.out.println("Day:");
        int dayStart=Integer.parseInt(keyboard.nextLine());
        System.out.println("Month:");
        int monthStart=Integer.parseInt(keyboard.nextLine());
        System.out.println("Year:");
        int yearStart=Integer.parseInt(keyboard.nextLine());

        System.out.println("Please enter end date: ");
        System.out.println("Day:");
        int dayEnd=Integer.parseInt(keyboard.nextLine());
        System.out.println("Month:");
        int monthEnd=Integer.parseInt(keyboard.nextLine());
        System.out.println("Year:");
        int yearEnd=Integer.parseInt(keyboard.nextLine());

        System.out.println("Proceed with the reservation? [Y/N]");
        String choice=keyboard.nextLine();
        if(choice.equalsIgnoreCase("y")){
            reservationController.addReservation(new Reservation(LocalDate.of(yearStart,monthStart,dayStart),
                    LocalDate.of(yearEnd,monthEnd,dayEnd),personController.getPersonByUsername(username).getPerson_id(),hotel_id, room_id));
            Period period=Period.between(LocalDate.of(yearStart,monthStart,dayStart),LocalDate.of(yearEnd,monthEnd,dayEnd));
            System.out.println("Reservation successful!!");
            System.out.println(room_id+" "+hotel_id);
            System.out.println("Total price will be: "+ period.getDays()*roomController.getRoomByID(room_id,hotel_id).getPrice());
        }else{
            System.out.println("We're sorry to hear that! :(");
            System.exit(0);
        }



    }
}
