package Dungeon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Character.Abilities.Mystic;
import Character.Combat.AttackObject;
import Character.Equipment.Armor;
import Character.Equipment.Artifact;
import Character.Equipment.ItemInterface;
import Character.Equipment.Weapon;
import Character.Mystics.MysticInterface;

public class Enemy {

    AttackObject attack = new AttackObject();
    String aggression = "Passive";
    int attackPattern = 0;
    ArrayList<AttackObject> pattern = new ArrayList<>();
    int block=0;
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
        setAggression();
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
        setAggression();
    }

    public void setAggression() {
        // Generate number 1-3 to set aggression level
        int random = (int) (Math.random() * 3 + 1);
        if(random == 1){
            aggression = "Passive";
        } else if(random == 2){
            aggression = "Neutral";
        } else if(random == 3){
            aggression = "Aggressive";
        }

        // Generate number 1-3 to set attack pattern
        if (aggression.equals("Passive")) {
            pattern.add(new AttackObject(0, damage/3, (int)(damage/1.5)));
            pattern.add(new AttackObject(0, (int)(damage/2), (damage/2)));
            pattern.add(new AttackObject(damage/2, (damage/2)));
            pattern.add(new AttackObject(damage/3, (int)(damage/1.5), (int)(damage/1.5)));
            pattern.add(new AttackObject(damage));
        } else if (aggression.equals("Neutral")) {
            pattern.add(new AttackObject(damage + (int)(damage/5)));
            pattern.add(new AttackObject((int)(damage/1.5), (int)(damage/2)));
            pattern.add(new AttackObject(damage, (int)(damage/4)));
            pattern.add(new AttackObject(0, (damage/2), (int)(damage/2)));
            pattern.add(new AttackObject(damage, (int)(damage/5)));
        } else if (aggression.equals("Aggressive")) {
            pattern.add(new AttackObject(damage + (int)(damage/3)));
            pattern.add(new AttackObject(damage, (int)(damage/5)));
            pattern.add(new AttackObject(damage/2, 0, (damage/2)));
            pattern.add(new AttackObject(damage/2, (damage/5)));
            pattern.add(new AttackObject(damage/3, (int)(damage/3), (int)(damage/3)));
        }

        generateAttack();
    }

    public AttackObject generateAttack(){
        if(pattern == null) setAggression();
        Random random = new Random();
        attack = pattern.get(random.nextInt(pattern.size()));
        return attack;
    }

    public void takeDamage(int damage){
        damage-= block;
        if(damage<= 0) damage = 0;
        health -= damage;
        if(health<=0){
            System.out.println(name + " has died");
        }
        block = 0;
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

    public Loot generateLoot(){
        Weapon wea = null;
        if (drops != null) {
            for (ItemInterface item : drops) {
                if (item instanceof Weapon) {
                    wea = (Weapon) item;
                }
            }
        }
        Armor arm = null;
        if (drops != null) {
            for (ItemInterface item : drops) {
                if (item instanceof Armor) {
                    arm = (Armor) item;
                }
            }
        }
        Artifact art = null;
        if (drops != null) {
            for (ItemInterface item : drops) {
                if (item instanceof Artifact) {
                    art = (Artifact) item;
                }
            }
        }

        MysticInterface myst = null;
        if (drops != null) {
            for (ItemInterface item : drops) {
                if (item instanceof Mystic) {
                    myst = (MysticInterface) item;
                }
            }
        }

        return new Loot(wea, arm, art, null, gold, "You found " + gold + " gold!", myst);
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public AttackObject getAttack() {
        return attack;
    }

    public void setAttack(AttackObject attack) {
        this.attack = attack;
    }
}
