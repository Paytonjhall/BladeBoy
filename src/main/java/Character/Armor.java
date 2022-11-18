package Character;

public class Armor implements ItemInterface {

    String name;
    int value;
    String description;
    double armorRating;

    public Armor(String name, int value, String description, double armorRating) {
        this.name = name;
        this.value = value;
        this.description = description;
        this.armorRating = armorRating;
    }

    public double getArmorRating() {
        return armorRating;
    }

    public void setArmorRating(double armorRating) {
        this.armorRating = armorRating;
    }

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
