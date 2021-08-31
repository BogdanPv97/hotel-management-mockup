package View;

import Controller.HotelController;
import Controller.PersonController;
import Controller.RoomController;
import Model.Hotel;
import Model.Room;

import java.util.Scanner;

public class LoggedView{
    Scanner keyboard;
    HotelController hotelController;
    PersonController personController;
    RoomController roomController;
    ReservationView reservationView;


    public LoggedView(){
        keyboard=new Scanner(System.in);
        hotelController=new HotelController();
        personController=new PersonController();
        roomController=new RoomController();
        reservationView=new ReservationView();

    }

    public void mainMenu(){
        System.out.println("--------MENU--------");
        System.out.println("1.View hotels");
        System.out.println("2.Exit");
    }

    public void viewRoomMenu(){
        System.out.println("--------MENU--------");
        System.out.println("1.Select the ID of the room you want to book:");
        System.out.println("2.Go back");
    }

    public void play(String username){
        mainMenu();
        boolean logged=true;

        while(logged){
            int choice=Integer.parseInt(keyboard.nextLine());
            switch(choice){
                case 1:
                    viewHotels(username);
                    break;
                case 2:
                    System.out.println("Thank you for using the app!");
                    logged=false;
                    break;
            }
        }
    }

    // TODO: 8/27/2021: implement viewHotels() method
    public void viewHotels(String username){
        String hotelName;
        Hotel hotelSelected=null;
        boolean validHotelName=false;
        int i=1;

        for(Hotel hotel:hotelController.getAllHotels()){
            System.out.println(i+". "+hotel.getHotelName()+" | "+hotel.getAddress()+" | "+hotel.getNumberOfRooms());
            i++;
        }
        while(!validHotelName) {
            System.out.println("Enter the hotel name");
            hotelName = keyboard.nextLine();
            if(hotelController.validHotelName(hotelName)){
                hotelSelected = hotelController.getHotelByName(hotelName);
                viewRooms(hotelSelected.getHotel_id(),username);
                validHotelName=true;
            }else{
                System.out.println("Hotel does not exist!");
            }
            System.out.println("Succes!");
        }
    }


    public void viewRooms(int hotel_id, String username){
        int choice=-1;
        int room_id=0;
        boolean validChoice=false;

        System.out.println("--------Rooms--------");
        for(Room room:roomController.getRoomsFromHotel(hotel_id)){
            System.out.println(room.getRoom_id()+" | "+room.getNumberOfPeople()+" | "+room.getPrice());
        }
        while(!validChoice) {
            viewRoomMenu();
            choice = Integer.parseInt(keyboard.nextLine());
            if(choice==1){
                System.out.println("Please enter the room ID:");
                room_id=Integer.parseInt(keyboard.nextLine());
                if(!hotelController.validRoomID(room_id,hotel_id)){
                    validChoice=true;
                    reservationView.play(room_id, hotel_id,username);
                }else{
                    System.out.println("Room ID not valid! Try again");
                }
            }else if(choice==0){
                viewHotels(username);
            }else{
                System.out.println("Invalid input! try again");
            }
        }
    }
}
