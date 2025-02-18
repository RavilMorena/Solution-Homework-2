// Главный класс

public class Main {
    public static void main(String[] args) {
        Room startRoom = new Room("Стартовая комната", "Здесь темно и сыро.");
        Player player = new Player("Герой", startRoom);
        MUDController controller = new MUDController(player);
        controller.runGameLoop();
    }
}
