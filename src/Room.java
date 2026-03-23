
public abstract class Room{
    protected int numberOfBeds;
    protected int squareFeet;
    protected double pricePerNight;

    public Room(int nb, int sf, double pn){
        this.numberOfBeds = nb;
        this.squareFeet = sf;
        this.pricePerNight = pn;
    }

    public abstract void displayRoomDetails();
}