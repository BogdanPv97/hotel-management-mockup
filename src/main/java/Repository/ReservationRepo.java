package Repository;

import Model.Hotel;
import Model.Reservation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationRepo {

    private String jdbcURL="jdbc:mysql://localhost:3306/rezervari_hotel";

    private String username="root";
    private String password="root";

    private Connection con=null;
    private Statement statement=null;

    public ReservationRepo(){
        try{
            con= DriverManager.getConnection(jdbcURL,username,password);
            statement=con.createStatement();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    private void executeStatement(String query){
        try{
            statement.execute(query);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    private int getReservationID(Reservation reservation){
        int reservation_id=0;
        String query=String.format("SELECT reservation_id FROM reservation WHERE person_id=%d AND hotel_id=%d",reservation.getPerson_id(),reservation.getHotel_id());
        try{
            ResultSet result=statement.executeQuery(query);
            if(result.next())
                reservation_id=result.getInt(1);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return reservation_id;
    }

    public void insertReservation(Reservation reservation){

        String insert=String.format("INSERT INTO reservation (dateStart, dateEnd, person_id, hotel_id, room_id) " +
                "values ('%s','%s',%d,%d,%d)",reservation.getDateStart(), reservation.getDateEnd(),reservation.getPerson_id(),
                                              reservation.getHotel_id(),reservation.getRoom_id());
        executeStatement(insert);
    }

    public void deleteReservation(Reservation reservation){

        String deleteReservation=String.format("DELETE FROM reservation WHERE reservation_id=%d",getReservationID(reservation));
        executeStatement(deleteReservation);
    }

    public void updateDateStart(Reservation reservation, Date newDateStart){

        String updateReservation=String.format("UPDATE reservation SET dateStart='%s' WHERE reservation_id=%d",newDateStart,getReservationID(reservation));
        executeStatement(updateReservation);
    }

    public void updateDateEnd(Reservation reservation, Date newDateEnd){

        String updateDateEnd=String.format("UPDATE reservation SET dateEnd='%s' WHERE reservation_id=%d",newDateEnd,getReservationID(reservation));
        executeStatement(updateDateEnd);
    }

    private ResultSet allReservations(){
        String query="SELECT * FROM reservation";
        try{
            return statement.executeQuery(query);
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Reservation> getAllReservations(){
        ResultSet set=allReservations();
        List<Reservation> reservations=new ArrayList<>();

        try{
            while(set.next()){
                reservations.add(new Reservation(set.getInt(1),set.getString(2),set.getString(3),set.getInt(4),set.getInt(5),set.getInt(6)));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return reservations;
    }

}
