package Character;

import java.util.Objects;
import java.util.Random;

public class Weapon implements ItemInterface {
    String name;
    int value;
    String description;
    int weaponDamage;
    String ability;
    String iconPath;
    String type;


    public Weapon(String name) {
        this.name = name;
    }

    public Weapon(String name, int value, String description, int weaponDamage) {
        this.name = name;
        this.value = value;
        this.description = description;
        this.weaponDamage = weaponDamage;
        type = setRandomType();
        setPath();
    }

    public Weapon(String name, int value, String description, int weaponDamage, String type) {
        this.name = name;
        this.value = value;
        this.description = description;
        this.weaponDamage = weaponDamage;
        this.type = type;
        setPath();
    }


    public String setRandomType(){
        Random random = new Random();
        return switch (random.nextInt(6)) {
            case 1 -> "Axe";
            case 2 -> "Dagger";
            case 3 -> "Spear";
            default -> "Sword";
        };
    }

    public void setPath(){
        Random random = new Random();
        int num = 0;
        if(Objects.equals(type, "Dagger")){ //Daggers: 10
            num = random.nextInt(9) + 1;
            iconPath = "Daggers/dagger" + num + ".jpg";
        } else if (Objects.equals(type, "Sword")){ //Sword 64
            num = random.nextInt(63) + 1;
            iconPath = "Swords/sword" + num + ".png";
        } else if (Objects.equals(type, "Axe")){ //Axe 20
            num = random.nextInt(19) + 1;
            iconPath = "Axes/" + num + ".png";
        } else if (Objects.equals(type, "Spear")){ //Spear 12
            num = random.nextInt(11) + 1;
            iconPath = "Spears/" + num + ".png";
        }
    }

    public int getWeaponDamage() {
        return weaponDamage;
    }

    public void setWeaponDamage(int weaponDamage) {
        this.weaponDamage = weaponDamage;
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

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString(){
        return "Name: " + name + " Value: " + value + " \nType: " + type + " Weapon Damage: " + weaponDamage;
    }
}
