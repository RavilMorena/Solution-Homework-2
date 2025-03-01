import java.util.ArrayList;
import java.util.List;

// Интерфейс для конструктора (Builder Pattern)
interface IDungeonBuilder {
    IDungeonBuilder setDungeonName(String name);
    IDungeonBuilder addRoom(Room room);
    IDungeonBuilder addNPC(NPC npc);
    Dungeon build();
}

// Класс подземелья (продукт)
class Dungeon {
    private String name;
    private List<Room> rooms = new ArrayList<>();
    private List<NPC> npcs = new ArrayList<>();

    public Dungeon(String name, List<Room> rooms, List<NPC> npcs) {
        this.name = name;
        this.rooms = rooms;
        this.npcs = npcs;
    }

    public void showDetails() {
        System.out.println("Dungeon: " + name);
        System.out.println("Rooms: " + rooms.size());
        System.out.println("NPCs: " + npcs.size());
    }
}

// Реализация конструктора подземелья
class SimpleDungeonBuilder implements IDungeonBuilder {
    private String name;
    private List<Room> rooms = new ArrayList<>();
    private List<NPC> npcs = new ArrayList<>();

    public IDungeonBuilder setDungeonName(String name) {
        this.name = name;
        return this;
    }

    public IDungeonBuilder addRoom(Room room) {
        rooms.add(room);
        return this;
    }

    public IDungeonBuilder addNPC(NPC npc) {
        npcs.add(npc);
        return this;
    }

    public Dungeon build() {
        return new Dungeon(name, rooms, npcs);
    }
}

// Интерфейс для клонирования объектов (Prototype Pattern)
interface CloneableGameEntity {
    CloneableGameEntity cloneEntity();
}

// Класс комнаты, поддерживающий клонирование
class Room implements CloneableGameEntity {
    private String name;
    private String description;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public CloneableGameEntity cloneEntity() {
        return new Room(name, description);
    }

    public void showRoom() {
        System.out.println("Room: " + name + " - " + description);
    }
}

// Класс NPC, поддерживающий клонирование
class NPC implements CloneableGameEntity {
    private String name;
    private String role;

    public NPC(String name, String role) {
        this.name = name;
        this.role = role;
    }

    @Override
    public CloneableGameEntity cloneEntity() {
        return new NPC(name, role);
    }

    public void showNPC() {
        System.out.println("NPC: " + name + " - " + role);
    }
}

// Главный класс с демонстрацией работы Builder и Prototype
public class DungeonGame {
    public static void main(String[] args) {
        System.out.println("=== Builder Pattern Demo ===");
        SimpleDungeonBuilder builder = new SimpleDungeonBuilder();
        Dungeon dungeon = builder.setDungeonName("Dark Cave")
                .addRoom(new Room("Entrance", "A dark and scary entrance."))
                .addNPC(new NPC("Goblin", "A small, sneaky enemy."))
                .build();
        dungeon.showDetails();

        System.out.println("\n=== Prototype Pattern Demo ===");
        Room prototypeRoom = new Room("Treasure Room", "Full of gold and gems.");
        Room clonedRoom = (Room) prototypeRoom.cloneEntity();
        clonedRoom.showRoom();

        System.out.println("\n=== Combined Pattern Demo ===");
        Room baseRoom = new Room("Hallway", "A long and eerie hallway.");
        Dungeon dungeonWithClones = builder.setDungeonName("Haunted Mansion")
                .addRoom(baseRoom)
                .addRoom((Room) baseRoom.cloneEntity())
                .build();
        dungeonWithClones.showDetails();
    }
}