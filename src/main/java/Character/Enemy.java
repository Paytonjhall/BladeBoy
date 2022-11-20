package Character;

import java.util.List;

public class Enemy {
    int health;
    int damage;
    double armorRating;
    String name;
    int xp;
    List<ItemInterface>drops;
    int gold;

    public Enemy(String name, int health, int damage, double armorRating, int xp, List<ItemInterface> drops, int gold) {
        this.health = health;
        this.damage = damage;
        this.armorRating = armorRating;
        this.name = name;
        this.xp = xp;
        this.drops = drops;
        this.gold = gold;
    }

    public void takeDamage(int damage){
        health -= damage;
        if(health<=0){
            System.out.println(name + " has died");
        }
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold=gold;
    }

    public void setDrops(List<ItemInterface> drops) {
        this.drops=drops;
    }

    public List<ItemInterface> getDrops() {
        return drops;
    }

    public int getXp(){
        return xp;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health=health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage=damage;
    }

    public double getArmorRating() {
        return armorRating;
    }

    public void setArmorRating(double armorRating) {
        this.armorRating=armorRating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public void setXp(int xp) {
        this.xp=xp;
    }
}
