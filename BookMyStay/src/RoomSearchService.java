import java.util.Map;

public class RoomSearchService {
    public void searchAvailableRooms(RoomInventory inventory, Room singleRoom, Room doubleRoom, Room suiteRoom) {
        Map<String, Integer> availability = inventory.getRoomAvailability();

        if (availability.get("SingleRoom") > 0) {
            singleRoom.displayRoomDetails();
            System.out.println("    Available Single Rooms: " + availability.get("SingleRoom"));
            System.out.println("-------------------------------------------------");
        }
        if (availability.get("DoubleRoom") > 0) {
            doubleRoom.displayRoomDetails();
            System.out.println("    Available Double Rooms: " + availability.get("DoubleRoom"));
            System.out.println("-------------------------------------------------");
        }
        if (availability.get("SuiteRoom") > 0) {
            suiteRoom.displayRoomDetails();
            System.out.println("    Available Suite Rooms: " + availability.get("SuiteRoom"));
            System.out.println("-------------------------------------------------");
        }

    }
}