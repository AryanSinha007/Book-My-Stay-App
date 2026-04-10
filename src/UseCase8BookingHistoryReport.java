
class UseCase8BookingHistoryReport {

    public static void welcomeMessage() {
        System.out.println("=================================================");
        System.out.println("              WELCOME TO BOOK MY STAY            ");
        System.out.println("=================================================");
        System.out.println("Find and book the perfect stay for your trip!");
        System.out.println();
        System.out.println("Author: Harshal");
        System.out.println("Version: 8.1");
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

        System.out.println("\nRoom Allocation Processing\n");

        RoomAllocationService allocationService = new RoomAllocationService();

        // Re-create queue again OR reuse logic
        BookingRequestQueue bookingQueue2 = new BookingRequestQueue();

        bookingQueue2.addRequest(new Reservation("Abhi", "SingleRoom"));
        bookingQueue2.addRequest(new Reservation("Subha", "DoubleRoom"));
        bookingQueue2.addRequest(new Reservation("Vanmathi", "SuiteRoom"));

        while (bookingQueue2.hasPendingRequests()) {
            Reservation r = bookingQueue2.getNextRequest();
            allocationService.allocateRoom(r, inventory);
        }

        System.out.println("\nAdd-On Service Selection\n");

        AddOnServiceManager serviceManager = new AddOnServiceManager();

        // Use a reservation ID from allocation
        String reservationId = "Single-1";

        // Create services
        AddOnService s1 = new AddOnService("Food", 1000);
        AddOnService s2 = new AddOnService("Laundry", 500);

        // Add services
        serviceManager.addService(reservationId, s1);
        serviceManager.addService(reservationId, s2);

        // Calculate total
        double totalCost = serviceManager.calculateTotalServiceCost(reservationId);

        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Total Add-On Cost: " + totalCost);

        System.out.println("\nBooking History and Reporting\n");

        BookingHistory history = new BookingHistory();

        // Add confirmed reservations (same as earlier)
        history.addReservation(new Reservation("Abhi", "Single"));
        history.addReservation(new Reservation("Subha", "Double"));
        history.addReservation(new Reservation("Vanmathi", "Suite"));

        BookingReportService reportService = new BookingReportService();
        reportService.generateReport(history);
    }
}