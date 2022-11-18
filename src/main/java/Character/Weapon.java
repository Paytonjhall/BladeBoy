package Character;

public class Weapon implements ItemInterface {
    String name;
    int value;
    String description;

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public void setDescription(String desc) {
        this.description = desc;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
