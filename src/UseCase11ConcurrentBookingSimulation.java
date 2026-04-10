import java.util.Scanner;

class UseCase11ConcurrentBookingSimulation {

    public static void welcomeMessage() {
        System.out.println("=================================================");
        System.out.println("              WELCOME TO BOOK MY STAY            ");
        System.out.println("=================================================");
        System.out.println("Find and book the perfect stay for your trip!");
        System.out.println();
        System.out.println("Author: Harshal");
        System.out.println("Version: 9.1");
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

        System.out.println("\nBooking Validation\n");

        Scanner scanner = new Scanner(System.in);

        ReservationValidator validator = new ReservationValidator();

        try {
            System.out.print("Enter guest name: ");
            String name = scanner.nextLine();

            System.out.print("Enter room type (Single/Double/Suite): ");
            String roomType = scanner.nextLine();

            // Validate input
            validator.validate(name, roomType, inventory);

            // If valid → create reservation
            Reservation r = new Reservation(name, roomType);

            bookingQueue.addRequest(r);

            System.out.println("Booking request added successfully.");

        } catch (InvalidBookingException e) {
            System.out.println("Booking failed: " + e.getMessage());
        } finally {
            scanner.close();
        }

        System.out.println("\nBooking Cancellation\n");

        CancellationService cancellationService = new CancellationService();

        // Register booking (from allocation)
        String cancelReservationId = "Single-1";
        cancellationService.registerBooking(cancelReservationId, "SingleRoom");

        // Cancel booking
        cancellationService.cancelBooking(cancelReservationId, inventory);

        // Show rollback history
        cancellationService.showRollbackHistory();

        // Show updated inventory
        System.out.println("\nUpdated Single Room Availability: " + inventory.getRoomAvailability().get("SingleRoom"));

        System.out.println("\nConcurrent Booking Simulation\n");

        // Create fresh inventory (small values to see effect)
        RoomInventory inventory2 = new RoomInventory();
        inventory2.updateAvailability("SingleRoom", 2);
        inventory2.updateAvailability("DoubleRoom", 1);
        inventory2.updateAvailability("SuiteRoom", 1);

        // Shared queue
        BookingRequestQueue bookingQueue3 = new BookingRequestQueue();

        bookingQueue3.addRequest(new Reservation("Abhi", "SingleRoom"));
        bookingQueue3.addRequest(new Reservation("Vanmathi", "DoubleRoom"));
        bookingQueue3.addRequest(new Reservation("Kural", "SuiteRoom"));
        bookingQueue3.addRequest(new Reservation("Subha", "SingleRoom"));

        // Shared allocation service
        RoomAllocationService allocationService2 = new RoomAllocationService();

        // Create threads
        Thread t1 = new Thread(
                new ConcurrentBookingProcessor(bookingQueue3, inventory2, allocationService2)
        );

        Thread t2 = new Thread(
                new ConcurrentBookingProcessor(bookingQueue3, inventory2, allocationService2)
        );

        // Start threads
        t1.start();
        t2.start();

        // Wait for completion
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Thread execution interrupted.");
        }

        // Show remaining inventory
        System.out.println("\nRemaining Inventory:");
        System.out.println("Single: " + inventory2.getRoomAvailability().get("SingleRoom"));
        System.out.println("Double: " + inventory2.getRoomAvailability().get("DoubleRoom"));
        System.out.println("Suite: " + inventory2.getRoomAvailability().get("SuiteRoom"));

    }
}