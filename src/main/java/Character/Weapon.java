package Character;

public class Weapon implements ItemInterface {
    String name;
    int value;
    String description;
    int weaponDamage;
    String ability;

    public Weapon(String name) {
        this.name = name;
    }

    public Weapon(String name, int value, String description, int weaponDamage) {
        this.name = name;
        this.value = value;
        this.description = description;
        this.weaponDamage = weaponDamage;
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

    @Override
    public String toString(){
        return "Name: " + name + " Value: " + value + " Description: " + description + " Weapon Damage: " + weaponDamage;
    }
}
