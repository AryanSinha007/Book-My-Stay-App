import java.util.*;

public class CancellationService {

    private Stack<String> releasedRoomIds;
    private Map<String, String> reservationRoomTypeMap;

    public CancellationService() {
        releasedRoomIds = new Stack<>();
        reservationRoomTypeMap = new HashMap<>();
    }

    public void registerBooking(String reservationId, String roomType) {
        reservationRoomTypeMap.put(reservationId, roomType);
    }

    public void cancelBooking(String reservationId, RoomInventory inventory) {

        if (!reservationRoomTypeMap.containsKey(reservationId)) {
            System.out.println("Invalid Reservation ID");
            return;
        }

        String roomType = reservationRoomTypeMap.get(reservationId);

        // push to stack
        releasedRoomIds.push(reservationId);

        // restore inventory
        Map<String, Integer> availability = inventory.getRoomAvailability();
        inventory.updateAvailability(roomType, availability.get(roomType) + 1);

        System.out.println("Booking cancelled successfully. Inventory restored for room type: "
                + roomType.replace("Room", ""));
    }

    public void showRollbackHistory() {

        System.out.println("\nRollback History (Most Recent First):");

        while (!releasedRoomIds.isEmpty()) {
            System.out.println("Released Reservation ID: " + releasedRoomIds.pop());
        }
    }
}