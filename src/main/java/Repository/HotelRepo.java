package Repository;

import Model.Hotel;
import com.mysql.cj.xdevapi.Result;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelRepo {

    private String jdbcURL="jdbc:mysql://localhost:3306/rezervari_hotel";

    private String username="root";
    private String password="root";

    private Connection con=null;
    private Statement statement=null;

    public HotelRepo(){
        try{
            con= DriverManager.getConnection(jdbcURL,username,password);
            statement=con.createStatement();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    private void executeStatement(String statementTBE){
        try{
            statement.execute(statementTBE);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }


    public int getHotelID(Hotel hotel){
        int hotel_id=0;
        String query=String.format("SELECT hotel_id FROM hotel WHERE name='%s' AND address='%s'",hotel.getHotelName(),hotel.getAddress());
        try {
            ResultSet result = statement.executeQuery(query);
            if(result.next())
                hotel_id=result.getInt(1);
        }catch(SQLException e){
            e.printStackTrace();
        }

        return hotel_id;
    }

    public void insertHotel(Hotel hotel){

        String insert=String.format("INSERT INTO Hotel (name,address,numberOfRooms) values ('%s','%s',%d)",hotel.getHotelName(),hotel.getAddress(),hotel.getNumberOfRooms());
        executeStatement(insert);
    }

    public void deleteHotel(Hotel hotel){

            String delete = String.format("DELETE FROM Hotel WHERE hotel_id=%d", getHotelID(hotel));
            executeStatement(delete);

        }


    public void updateName(Hotel hotel, String newName){

        String updateName=String.format("UPDATE Hotel SET name='%s' WHERE hotel_id=%d",newName,getHotelID(hotel));
        executeStatement(updateName);
    }

    public void updateAddress(Hotel hotel, String newAddress){

        String updateAddress=String.format("UPDATE Hotel SET address='%s' WHERE hotel_id=%d",newAddress,getHotelID(hotel));
        executeStatement(updateAddress);
    }

    public void updateNumberOfRooms(Hotel hotel,int newNumberOfRooms){

        String updateNumberOfRooms=String.format("UPDATE Hotel SET numberOfRooms=%d WHERE hotel_id=%d",newNumberOfRooms,getHotelID(hotel));
        executeStatement(updateNumberOfRooms);
    }

    public ResultSet allHotel(){
        executeStatement("SELECT * FROM hotel");
        try{
            return statement.getResultSet();
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Hotel> getAllHotel(){
        ResultSet set=allHotel();

        List<Hotel> hotels=new ArrayList<>();

        try{
            while(set.next()){
                hotels.add(new Hotel(set.getInt(1),set.getString(2),set.getString(3),set.getInt(4)));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return hotels;
    }

    public Hotel getHotelByID(int hotel_id){
        String query=String.format("SELECT * FROM hotel WHERE hotel_id=%d",hotel_id);
        Hotel hotel=null;
        try{
            ResultSet result=statement.executeQuery(query);
            if(result.next()) {
                hotel = new Hotel(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return hotel;
    }
}
