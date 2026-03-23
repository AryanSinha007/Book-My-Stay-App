public class DoubleRoom extends Room{
    public DoubleRoom(){
        super(2, 400, 2500.0);
    }
    public void displayRoomDetails(){
        System.out.println();
        System.out.println("-------------------------------------------------");
        System.out.println("    Double Room -");
        System.out.println("    Number of Beds:" + numberOfBeds);
        System.out.println("    Square Feet of Room:" + squareFeet);
        System.out.println("    Price per Night:" + pricePerNight);
        System.out.println("-------------------------------------------------");
    }
}