class UseCase5BookingRequestQueue {

    public static void welcomeMessage() {
        System.out.println("=================================================");
        System.out.println("              WELCOME TO BOOK MY STAY            ");
        System.out.println("=================================================");
        System.out.println("Find and book the perfect stay for your trip!");
        System.out.println();
        System.out.println("Author: Harshal");
        System.out.println("Version: 4.1");
        System.out.println("-------------------------------------------------");
        System.out.println("            Hotel Room Inventory Status");

    }

    public static void main(String[] args) {
        welcomeMessage();
        SingleRoom Single = new SingleRoom();
        DoubleRoom Double = new DoubleRoom();
        SuiteRoom Suite = new SuiteRoom();
        RoomInventory inventory = new RoomInventory();
        RoomSearchService searchService = new RoomSearchService();

        // SngR.displayRoomDetails();
        // DbR.displayRoomDetails();
        // StR.displayRoomDetails();
        searchService.searchAvailableRooms(inventory, Single, Double, Suite);

        System.out.println("Booking Request Queue - \n");

        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        Reservation r1 = new Reservation("Abhi", "Single");
        Reservation r2 = new Reservation("Subha", "Double");
        Reservation r3 = new Reservation("Vanmathi", "Suite");

        bookingQueue.addRequest(r1);
        bookingQueue.addRequest(r2);
        bookingQueue.addRequest(r3);

        while (bookingQueue.hasPendingRequests()) {
            Reservation r = bookingQueue.getNextRequest();

            System.out.println("Guest Name: " + r.getGuestName());
            System.out.println("Room Type: " + r.getRoomType());
            System.out.println("-------------------------------------------------");

        }

    }
}