public class BookingReportService {

    public void generateReport(BookingHistory history) {

        System.out.println("\nBooking History Report\n");

        for (Reservation r : history.getConfirmedReservations()) {
            System.out.println("Guest: " + r.getGuestName()
                    + ", Room Type: " + r.getRoomType());
        }
    }
}