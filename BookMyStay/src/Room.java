 public abstract class Room {
    protected int numberOfBeds;
    protected int squareFeet;
    protected double pricePerNight;

    public Room(int nb, int sf, double pn) {
        this.numberOfBeds = nb;
        this.squareFeet = sf;
        this.pricePerNight = pn;
    }

    public void displayRoomDetails() {
        System.out.println();
        System.out.println("-------------------------------------------------");
        System.out.println("    Number of Beds:" + numberOfBeds);
        System.out.println("    Square Feet of Room:" + squareFeet);
        System.out.println("    Price per Night:" + pricePerNight);
        System.out.println("-------------------------------------------------");
    }
}

