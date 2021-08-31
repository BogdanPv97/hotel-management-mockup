package Repository;

import Model.Hotel;
import Model.Room;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomRepo {

    private String jdbcURL="jdbc:mysql://localhost:3306/rezervari_hotel";

    private String username="root";
    private String password="root";

    private Connection con = null;
    private Statement statement = null;

    public RoomRepo(){
        try {
            con = DriverManager.getConnection(jdbcURL, username, password);
            statement = con.createStatement();
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

    private int getRoomID(Room room){
        int roomID=0;
        String query=String.format("SELECT room_id FROM room WHERE hotel_id=%d AND noOfPerson=%d AND price=%d",room.getHotel_id(),room.getNumberOfPeople(),room.getPrice());
        try{
            ResultSet result=statement.executeQuery(query);
            if(result.next())
                roomID= result.getInt(1);
        }catch(SQLException e){}

        return roomID;
    }

    public void insertRoom(Room room, int hotel_id){

        String insert=String.format("INSERT INTO room (room_id, hotel_id, noOfPerson, price, available) " +
                "values (%d,%d,%d,%.2f,%b)",room.getRoom_id(),hotel_id,room.getNumberOfPeople(),room.getPrice(),room.isAvailable());
        executeStatement(insert);
    }

    public void deleteRoom(Room room, int hotel_id){

        String delete=String.format("DELETE FROM room WHERE room_id=%d AND hotel_id=%d",getRoomID(room),hotel_id);
        executeStatement(delete);
    }

    public void updateNoOfPerson(Room room, int hotel_id, int newNumberOfPeople){

        String updateNoOfPerson=String.format("UPDATE room SET noOfPerson=%d WHERE room_id=%d AND hotel_id=%d",newNumberOfPeople,getRoomID(room),hotel_id);
        executeStatement(updateNoOfPerson);
    }

    public void updatePrice(Room room, int hotel_id, double newPrice){

        String updatePrice=String.format("UPDATE room SET price=%.2f WHERE room_id=%d AND hotel_id=%d",newPrice,getRoomID(room),hotel_id);
        executeStatement(updatePrice);
    }

    public void updateStatus(Room room, int hotel_id, boolean status){

        String updateStatus=String.format("UPDATE room SET available=%b WHERE room_id=%d AND hotel_id=%d",status,getRoomID(room),hotel_id);
    }

    private ResultSet allRooms(int hotel_id){
        String query=String.format("SELECT * FROM room WHERE hotel_id=%d",hotel_id);
        try {
            return statement.executeQuery(query);
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Room> getAllRooms(int hotel_id){
        ResultSet set=allRooms(hotel_id);

        List<Room> rooms=new ArrayList<>();
        try {
            while (set.next()) {
                rooms.add(new Room(set.getInt(1),set.getInt(2),set.getDouble(3),set.getBoolean(4),set.getInt(5)));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return rooms;
    }

    public Room getRoomByID(int room_id, int hotel_id){
        String query=String.format("SELECT * FROM room WHERE room_id=%d AND hotel_id=%d",room_id,hotel_id);
        try {
            ResultSet result = statement.executeQuery(query);
            if(result.next()){
                return new Room(result.getInt(1),result.getInt(2),result.getDouble(3),result.getBoolean(4),result.getInt(5));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Room> getRoomsFromHotel(int hotel_id){
        String query=String.format("SELECT * FROM room WHERE hotel_id=%d",hotel_id);
        List<Room> allRoomsFromHotel=new ArrayList<>();
        try {
            ResultSet set = statement.executeQuery(query);
            while(set.next()){
                allRoomsFromHotel.add(new Room(set.getInt(1),set.getInt(2),set.getDouble(3),set.getBoolean(4),set.getInt(5)));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return allRoomsFromHotel;
    }
}
