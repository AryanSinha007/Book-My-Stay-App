
public class SuiteRoom extends Room{
    public SuiteRoom(){
        super(3, 750, 5000.0);
    }
    public void displayRoomDetails(){
        System.out.println();
        System.out.println("-------------------------------------------------");
        System.out.println("    SingleRoom - ");
        System.out.println("    Number of Beds:" + numberOfBeds);
        System.out.println("    Square Feet of Room:" + squareFeet);
        System.out.println("    Price per Night:" + pricePerNight);
        System.out.println("-------------------------------------------------");
    }
}