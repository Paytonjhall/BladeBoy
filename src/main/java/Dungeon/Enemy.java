package Dungeon;

import java.util.List;
import Character.Equipment.ItemInterface;
public class Enemy {
    int health;
    int maxHealth;
    int damage;
    double armorRating;
    String name;
    int xp;
    List<ItemInterface>drops;
    int gold;
    String iconPath = "src/Assets/Portraits/Icons_24.png";

    public Enemy(String name, int health, int damage, double armorRating, int xp, List<ItemInterface> drops, int gold) {
        this.health = health;
        this.maxHealth = health;
        this.damage = damage;
        this.armorRating = armorRating;
        this.name = name;
        this.xp = xp;
        this.drops = drops;
        this.gold = gold;
    }

    public Enemy(String name, int health, int damage, double armorRating, int xp, List<ItemInterface> drops, int gold, String iconPath) {
        this.health = health;
        this.maxHealth = health;
        this.damage = damage;
        this.armorRating = armorRating;
        this.name = name;
        this.xp = xp;
        this.drops = drops;
        this.gold = gold;
        this.iconPath = iconPath;
    }

    public void takeDamage(int damage){
        health -= damage;
        if(health<=0){
            System.out.println(name + " has died");
        }
    }

    public void heal(int health){
        this.health += health;
        if(this.health > this.maxHealth){
            this.health = this.maxHealth;
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

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth=maxHealth;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public String toString(){
        String string = "Name: " + name + "\nHealth: " + health + "\nDamage: " + damage + "\nArmor Rating: " + armorRating + "\nXP: " + xp + "\nGold: " + gold;
        if(drops != null){
            for(ItemInterface item : drops){
                string += "\n\t\t" + item.toString();
            }
        }
        return string;
    }
}
