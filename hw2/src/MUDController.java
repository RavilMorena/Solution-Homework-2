// Контроллер игры
import java.util.Scanner;

public class MUDController {
    private final Player player;
    private boolean running;

    public MUDController(Player player) {
        this.player = player;
        this.running = true;
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

    public void handleInput(String input) {
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        String argument = parts.length > 1 ? parts[1] : "";

        if (input.equals("help")) {
            showHelp();
            return;
        }

        switch (command) {
            case "look":
                System.out.println(player.getCurrentRoom().describe());
                break;
            case "move":
                Room nextRoom = player.getCurrentRoom().getExit(argument);
                if (nextRoom != null) {
                    player.move(nextRoom);
                    System.out.println("Вы переместились в " + nextRoom.getName());
                } else {
                    System.out.println("Вы не можете туда идти!");
                }
                break;
            case "pick":
                Item item = ItemFactory.createItem(argument);
                player.pickUpItem(item);
                System.out.println("Вы подобрали " + item.getName());
                break;
            case "inventory":
                System.out.println(player.getInventory());
                break;
            case "quit":
                running = false;
                break;
            default:
                System.out.println("Неизвестная команда.");
        }
    }

    // Сюда добавляем showHelp()
    private void showHelp() {
        System.out.println("Доступные команды:");
        System.out.println("- look            : Осмотреть текущую комнату");
        System.out.println("- move <направление> : Переместиться (forward, back, left, right)");
        System.out.println("- pick <предмет>  : Поднять предмет");
        System.out.println("- inventory       : Посмотреть инвентарь");
        System.out.println("- help            : Показать это меню");
        System.out.println("- quit            : Выйти из игры");
    }
}