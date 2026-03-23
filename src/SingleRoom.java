
public class SingleRoom extends Room {
    public SingleRoom(){
        super(1, 250, 1500.0);
    }
    public void displayRoomDetails(){
        System.out.println("-------------------------------------------------");
        System.out.println("    SingleRoom - ");
        System.out.println("    Number of Beds:" + numberOfBeds);
        System.out.println("    Square Feet of Room:" + squareFeet);
        System.out.println("    Price per Night:" + pricePerNight);
        System.out.println("-------------------------------------------------");
    }
}