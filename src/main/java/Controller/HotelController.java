package Controller;

import Model.Hotel;
import Model.Room;
import Repository.HotelRepo;
import Repository.RoomRepo;

import java.util.ArrayList;
import java.util.List;

public class HotelController {

    ArrayList<Hotel> hotels;
    HotelRepo hotelRepo;
    RoomRepo roomRepo;

    public HotelController(){
        hotels=new ArrayList<>();
        hotelRepo=new HotelRepo();
        roomRepo=new RoomRepo();
        loadRepo();
    }

    public Hotel getHotelByName(String hotelName){
        for(Hotel hotel:hotels){
            if(hotel.getHotelName().equals(hotelName))
                return hotel;
        }
        return null;
    }

    public boolean validHotelName(String hotelName){
        for(Hotel hotel:hotels){
            if(hotel.getHotelName().equals(hotelName))
                return true;
        }
        return false;
    }

    public Hotel getHotelByID(int hotel_id){
        Hotel hotel=hotelRepo.getHotelByID(hotel_id);
        return hotel;
    }

    //CRUD
// TODO: 8/27/2021: Check the link between rooms from database and rooms<> from Hotel class //DONE
    public void addHotel(Hotel hotel){
        hotelRepo.insertHotel(hotel);

        int hotelID=hotelRepo.getHotelID(hotel);
        ArrayList<Room> rooms=new ArrayList<>();
        //Create rooms for hotel
        for(int i=1;i<=hotel.getNumberOfRooms();i++){
            roomRepo.insertRoom(new Room(i,0,0,true),hotelID);
            rooms.add(new Room(i,0,0,true));
        }
        hotel.setRooms(rooms);
    }

    public void deleteHotel(Hotel hotel){
        hotelRepo.deleteHotel(hotel);
    }

    public void deleteHotelByName(String name){
        hotelRepo.deleteHotel(getHotelByName(name));
    }

    public void updateName(String hotelName, String newName){
        hotelRepo.updateName(getHotelByName(hotelName),newName);
    }

    public void updateAddress(String hotelName, String newAddress){
        hotelRepo.updateAddress(getHotelByName(hotelName),newAddress);
    }

    public void updateNoOfRooms(String hotelName,int newNumberOfRooms){
        hotelRepo.updateNumberOfRooms(getHotelByName(hotelName),newNumberOfRooms);
    }

    public void display(){
        for(Hotel hotel:hotels){
            System.out.println(hotel);
        }
    }

    public List<Hotel> getAllHotels(){
        List<Hotel> set=hotelRepo.getAllHotel();
        return set;
    }

    public boolean validRoomID(int room_id, int hotel_id){
        Hotel hotel=getHotelByID(hotel_id);

        for(Room room:hotel.getRooms()){
            if(room.getRoom_id()==room_id)
                return true;
        }
        return false;
    }

    public void loadRepo(){
        hotels.addAll(hotelRepo.getAllHotel());
    }
}
