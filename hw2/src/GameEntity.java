// Абстрактный класс для всех игровых сущностей
public abstract class GameEntity {
    protected String name;

    public GameEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract String describe();
}