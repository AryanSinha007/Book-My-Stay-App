import java.util.HashMap;
import java.util.Map;

public class RoomInventory {
    private Map<String, Integer> roomAvailability;

    public RoomInventory(){
        roomAvailability = new HashMap<>();
        initializeInventory();
    }

    private void initializeInventory(){
        roomAvailability.put("SingleRoom", 25);
        roomAvailability.put("DoubleRoom", 50);
        roomAvailability.put("SuiteRoom", 10);
    }

    public Map<String, Integer> getRoomAvailability(){
        return roomAvailability;
    }

    public void updateAvailability(String roomType, int count){
        roomAvailability.put(roomType, count);
    }
}