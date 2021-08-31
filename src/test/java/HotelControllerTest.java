import Controller.HotelController;
import Model.Hotel;
import org.junit.Test;

public class HotelControllerTest {

    @Test
    public void insertHotelTest(){
        HotelController controller=new HotelController();

        Hotel hotel=new Hotel("Continental","Bdul Take Ionescu",20);
        //controller.addHotel(hotel);
        Hotel hotel2=new Hotel("Hotel Central","P-ta Victoriei",20);
        controller.addHotel(hotel2);
    }
}
