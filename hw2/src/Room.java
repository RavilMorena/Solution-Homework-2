// Класс комнаты
import java.util.HashMap;
import java.util.Map;

public class Room extends GameEntity {
    private String description;
    private Map<String, Room> exits;

    public Room(String name, String description) {
        super(name);
        this.description = description;
        this.exits = new HashMap<>();
    }

    public void setExit(String direction, Room room) {
        exits.put(direction, room);
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    @Override
    public String describe() {
        return name + ": " + description;
    }
}