package Character;

public class Armor implements ItemInterface {
    //Max armor is 900; This blocks 90% of damage

    String name;
    int value;
    String description;
    double armorRating;
    int healthIncrease = 0;
    int protection = 0;
    String iconPath = "src/Assets/Armor/breastplate.png";

    public Armor(String name) {
        this.name=name;
    }

    public Armor(String name, int value, String description, double armorRating, int healthIncrease) {
        this.name=name;
        this.value=value;
        this.description=description;
        this.armorRating=armorRating;
        this.healthIncrease=healthIncrease;
    }

    public Armor(String name, int value, String description, double armorRating, int healthIncrease, int protection) {
        this.name=name;
        this.value=value;
        this.description=description;
        this.armorRating=armorRating;
        this.healthIncrease=healthIncrease;
        this.protection=protection;
    }

    public Armor(String name, int value, String description, double armorRating) {
        this.name = name;
        this.value = value;
        this.description = description;
        this.armorRating = armorRating;
    }

    public int getHealthIncrease() {
        return healthIncrease;
    }

    public void setHealthIncrease(int healthIncrease) {
        this.healthIncrease=healthIncrease;
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

    public int getProtection() {
        return protection;
    }

    public void setProtection(int protection) {
        this.protection=protection;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    @Override
    public String toString(){
        return name + ": Value(" + value + " Gold), Armor Rating(" + armorRating + "), Health Bonus(" + healthIncrease + "), Protection(" + protection +")";
    }
}
