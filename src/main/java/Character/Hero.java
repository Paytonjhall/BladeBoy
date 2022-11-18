package Character;

public class Hero {
    Armor armor;
    Weapon weapon;
    Artifact artifact;
    int health;

    public Hero(Armor armor, Weapon weapon, int health) {
        this.armor = armor;
        this.weapon = weapon;
        this.health = health;
    }
}
