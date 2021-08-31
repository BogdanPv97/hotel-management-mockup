package Controller;

import Model.Reservation;
import Repository.ReservationRepo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservationController {

    List<Reservation> reservations;
    ReservationRepo reservationRepo;

    public ReservationController(){
        reservations=new ArrayList<>();
        reservationRepo=new ReservationRepo();
        loadRepo();
    }

    //CRUD
    public void addReservation(Reservation reservation){
        reservationRepo.insertReservation(reservation);
    }

    public void removeReservation(Reservation reservation){
        reservationRepo.deleteReservation(reservation);
    }

    public void updateStartDate(Reservation reservation, Date newStartDate){
        reservationRepo.updateDateStart(reservation,java.sql.Date.valueOf(String.valueOf(newStartDate)));
    }

    public void updateEndDate(Reservation reservation, Date newEndDate){
        reservationRepo.updateDateEnd(reservation,java.sql.Date.valueOf(String.valueOf(newEndDate)));
    }

    public void display(){
        for(Reservation res:reservations){
            System.out.println(res);
        }
    }

    public void loadRepo(){
        reservations=reservationRepo.getAllReservations();
    }
}
