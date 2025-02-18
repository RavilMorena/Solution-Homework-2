// Класс игрока
import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private Room currentRoom;
    private List<Item> inventory;

    public Player(String name, Room startRoom) {
        this.name = name;
        this.currentRoom = startRoom;
        this.inventory = new ArrayList<>();
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void move(Room newRoom) {
        this.currentRoom = newRoom;
    }

    public void pickUpItem(Item item) {
        inventory.add(item);
    }

    public String getInventory() {
        return inventory.isEmpty() ? "Инвентарь пуст" : inventory.toString();
    }
}