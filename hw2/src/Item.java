// Класс предмета

public class Item extends GameEntity {
    public Item(String name) {
        super(name);
    }

    @Override
    public String describe() {
        return "Предмет: " + name;
    }

    @Override
    public String toString() {
        return name;
    }

}