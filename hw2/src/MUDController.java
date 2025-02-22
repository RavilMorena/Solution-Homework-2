import java.util.*;

public class MUDController {
    private String playerName;
    private Room currentRoom;
    private List<String> inventory;
    private boolean running;

    public MUDController(String playerName) {
        this.playerName = playerName;
        this.inventory = new ArrayList<>();
        this.running = true;
        setupGame();
    }

    private void setupGame() {
        // Создание комнат
        Room startRoom = new Room("Стартовая комната", "Здесь темно и сыро.");
        Room secondRoom = new Room("Вторая комната", "Здесь холодно и страшно.");
        Room thirdRoom = new Room("Третья комната", "В этой комнате пахнет едой.");

        // Устанавливаем связи между комнатами
        startRoom.setExit("forward", secondRoom);
        secondRoom.setExit("back", startRoom);
        secondRoom.setExit("right", thirdRoom);
        thirdRoom.setExit("left", secondRoom);

        this.currentRoom = startRoom;
    }

    public void runGameLoop() {
        Scanner scanner = new Scanner(System.in);
        while (running) {
            System.out.print("> ");
            String input = scanner.nextLine();
            handleInput(input);
        }
        scanner.close();
    }

    private void handleInput(String input) {
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        String argument = parts.length > 1 ? parts[1] : "";

        switch (command) {
            case "look":
                System.out.println(currentRoom.describe());
                break;
            case "move":
                Room nextRoom = currentRoom.getExit(argument);
                if (nextRoom != null) {
                    currentRoom = nextRoom;
                    System.out.println("Вы переместились в " + currentRoom.getName());
                } else {
                    System.out.println("Вы не можете туда идти!");
                }
                break;
            case "pick":
                if (!argument.isEmpty()) {
                    inventory.add(argument);
                    System.out.println("Вы подобрали " + argument);
                } else {
                    System.out.println("Что вы хотите подобрать?");
                }
                break;
            case "inventory":
                System.out.println(inventory.isEmpty() ? "Инвентарь пуст" : "Ваш инвентарь: " + inventory);
                break;
            case "help":
                showHelp();
                break;
            case "quit":
                running = false;
                System.out.println("Игра завершена.");
                break;
            default:
                System.out.println("Неизвестная команда. Введите 'help' для списка команд.");
        }
    }

    private void showHelp() {
        System.out.println("Доступные команды:");
        System.out.println("- look            : Осмотреть текущую комнату");
        System.out.println("- move <направление> : Переместиться (forward, back, left, right)");
        System.out.println("- pick <предмет>  : Поднять предмет");
        System.out.println("- inventory       : Посмотреть инвентарь");
        System.out.println("- help            : Показать это меню");
        System.out.println("- quit            : Выйти из игры");
    }

    public static void main(String[] args) {
        System.out.print("Введите имя игрока: ");
        Scanner scanner = new Scanner(System.in);
        String playerName = scanner.nextLine();
        MUDController game = new MUDController(playerName);
        game.runGameLoop();
        scanner.close();
    }

    // Вложенный класс Комната
    private static class Room {
        private String name;
        private String description;
        private Map<String, Room> exits;

        public Room(String name, String description) {
            this.name = name;
            this.description = description;
            this.exits = new HashMap<>();
        }

        public void setExit(String direction, Room room) {
            exits.put(direction, room);
        }

        public Room getExit(String direction) {
            return exits.get(direction);
        }

        public String getName() {
            return name;
        }

        public String describe() {
            return name + ": " + description;
        }
    }
}