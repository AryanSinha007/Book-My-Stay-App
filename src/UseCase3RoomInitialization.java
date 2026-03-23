public class UseCase3RoomInitialization {
    public static void welcomeMessage(){
        System.out.println("=================================================");
        System.out.println("              WELCOME TO BOOK MY STAY            ");
        System.out.println("=================================================");
        System.out.println("Find and book the perfect stay for your trip!");
        System.out.println();
        System.out.println("Author: Harshal");
        System.out.println("Version: 3.1");
        System.out.println("-------------------------------------------------");
        System.out.println("            Hotel Room Inventory Status");

    }
    public static void main(String[] args){
        welcomeMessage();
        SingleRoom SngR = new SingleRoom();
        DoubleRoom DbR = new DoubleRoom();
        SuiteRoom StR = new SuiteRoom();

        SngR.displayRoomDetails();
        DbR.displayRoomDetails();
        StR.displayRoomDetails();
    }
}
