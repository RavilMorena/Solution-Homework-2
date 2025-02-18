// Фабричный метод для предметов

public class ItemFactory {
    public static Item createItem(String itemName) {
        return new Item(itemName);
    }
}