package Controller;

import Model.Hotel;
import Model.Room;
import Repository.RoomRepo;


import java.util.ArrayList;
import java.util.List;

public class RoomController {

    List<Room> rooms;
    RoomRepo roomRepo;
    Hotel hotel;

    public RoomController(){
        rooms=new ArrayList<>();
        roomRepo=new RoomRepo();
        hotel=new Hotel();
        loadRepo();
    }

    //CRUD

    public void addRoom(Room room, Hotel hotel){
        roomRepo.insertRoom(room, hotel.getHotel_id());
    }

    public void removeRoom(Room room, Hotel hotel){
        roomRepo.deleteRoom(room,hotel.getHotel_id());
    }

    public void updateNoOfPeople(Room room, Hotel hotel,int newNumberOfPeople){
        roomRepo.updateNoOfPerson(room,hotel.getHotel_id(),newNumberOfPeople);
    }

    public void updatePrice(Room room, Hotel hotel,double newPrice){
        roomRepo.updatePrice(room,hotel.getHotel_id(),newPrice);
    }

    public void display(Hotel hotel){
        for(Room room:hotel.getRooms()){
            System.out.println(room);
        }
    }

    public Room getRoomByID(int room_id,int hotel_id){

        return roomRepo.getRoomByID(room_id,hotel_id);
    }

    public List<Room> getRoomsFromHotel(int hotel_id){
        List<Room> roomsFromHotel=new ArrayList<>();
        roomsFromHotel.addAll(roomRepo.getRoomsFromHotel(hotel_id));

        return roomsFromHotel;
    }

    public void loadRepo(){
        rooms=roomRepo.getAllRooms(hotel.getHotel_id());
    }
}
