import Model.Hotel;
import Repository.HotelRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class HotelRepoTest {

   // HotelRepo repo;

    @BeforeEach
    public void preconditions(){
        //repo=new HotelRepo();
    }

    @Test
    public void insertHotelTest(){

        HotelRepo repo=new HotelRepo();

        for(int i=0;i<21;i++){
            repo.insertHotel(new Hotel("Name"+i,"Address"+i,100+i));
        }
    }

    @Test
    public void getHotelIDTest(){
        Hotel hotel=new Hotel("aaa","bbb",10);


       // repo.insertHotel(hotel);
       // repo.updateAddress(hotel, "ccc");
    }

    @Test
    public void removeTest(){
        Hotel hotel=new Hotel("ToBeDeleted","ToBeDeleted",1000);
        HotelRepo repo=new HotelRepo();
       // repo.insertHotel(hotel);
        repo.deleteHotel(hotel);

    }
}
