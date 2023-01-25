package Dungeon;
import Character.*;
import Character.Town.ArmorGenerator;
import Character.Town.ArtifactGenerator;
import Character.Town.WeaponGenerator;
import Game.AssetPath;

public class ChestLoot {
    //This class produces the loot found in dungeon chests.
    //Below is the percent chance of each item.
    //Gold = 40%, 25% a little, 10% medium, 5% lots!
    //Potion = 30%, 15% chance of health potion, 15% chance of other potions
    //Item = 30%, weapon = 10%, armor = 10%, accessory = 10%
    //Items are returned inside loot object
    AssetPath ap = new AssetPath();
    WeaponGenerator wg = new WeaponGenerator();
    ArmorGenerator ag = new ArmorGenerator();
    ArtifactGenerator artg = new ArtifactGenerator();

    public ChestLoot(){

    }

    public Loot generateLoot(int level) {
        Weapon weapon = null;
        Armor armor = null;
        Artifact artifact = null;
        Potion potion = null;
        int gold = 0;
        int roll = (int) (Math.random() * 100);

        if(isBetween(roll, 0, 25)){
            //little gold
            System.out.println("You found a little gold!");
            gold = 25 * level + (int)(Math.random() * 25);
        } else if(isBetween(roll, 26, 35)){
            //medium gold
            gold = 45 * level + (int)(Math.random() * 45);
            System.out.println("You found a medium amount of gold!");
        } else if(isBetween(roll, 36, 40)){
            //lots of gold
            System.out.println("You found a lot of gold!");
            gold = 85 * level + (int)(Math.random() * 85);
        } else if(isBetween(roll, 41, 55)){
            potion = new Potion("Heal Potion", level * 6 + ((int)(Math.random()*3) *level), level * 10);
            potion.setIconPath(ap.healthPotion);
            System.out.println("You found a health potion!");
        } else if(isBetween(roll, 56, 70)){
            //other potion --> not made yet
            System.out.println("You found a potion!");
        } else if(isBetween(roll, 71, 80)){
            weapon = wg.generateWeapons(level,1).get(0);
            System.out.println("You found a weapon! " + weapon.getName());
        } else if(isBetween(roll, 81, 90)){
            armor = ag.generateArmor(level, 1).get(0);
            System.out.println("You found armor! " + armor.getName());
        } else if(isBetween(roll, 91, 100)){
            artifact = artg.generateArtifact(level, 1).get(0);
            System.out.println("You found an artifact! " + artifact.getName());
        } else {
            System.out.println("Error in ChestLoot.java");
        }
        return new Loot(weapon, armor, artifact, potion, gold);
    }

    public static boolean isBetween(int x, int lower, int upper) {
        return lower <= x && x <= upper;
    }

}
