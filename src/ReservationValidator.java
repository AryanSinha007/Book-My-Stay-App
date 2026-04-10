import java.util.*;

public class ReservationValidator {

    public void validate(String guestName, String roomType, RoomInventory inventory)
            throws InvalidBookingException {

        // check guest name
        if (guestName == null || guestName.trim().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty.");
        }

        // check room type (CASE SENSITIVE as per question)
        if (!roomType.equals("Single") &&
                !roomType.equals("Double") &&
                !roomType.equals("Suite")) {

            throw new InvalidBookingException("Invalid room type selected.");
        }

        // optional: check availability
        Map<String, Integer> availability = inventory.getRoomAvailability();

        String key = roomType + "Room"; // convert to match inventory

        if (availability.get(key) == 0) {
            throw new InvalidBookingException("No rooms available for selected type.");
        }
    }
}