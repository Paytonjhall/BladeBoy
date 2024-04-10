package Dungeon;
import Character.Equipment.Armor;
import Character.Hero;
import Character.Equipment.Artifact;
import Character.Equipment.Potion;
import Character.Equipment.Weapon;
import Character.Town.ArmorGenerator;
import Character.Town.ArtifactGenerator;
import Character.Town.PotionGenerator;
import Character.Town.WeaponGenerator;
import Game.AssetPath;
import Character.Mystics.*;

public class ChestLoot {
    //This class produces the loot found in dungeon chests.
    //Below is the percent chance of each item.
    //Gold = 40%, 25% a little, 10% medium, 5% lots!
    //Potion = 30%, 15% chance of health potion, 15% chance of other potions
    //Item = 30%, weapon = 10%, armor = 10%, accessory = 10%
    //Items are returned inside loot object
    AssetPath ap = new AssetPath();
    WeaponGenerator wg;
    ArmorGenerator ag = new ArmorGenerator();
    ArtifactGenerator artg = new ArtifactGenerator();

    MysticGenerator mg = new MysticGenerator();

    public ChestLoot(){

    }

    public Loot generateLoot(Hero hero) {
        wg = new WeaponGenerator(hero);
        int level = hero.getLevel();
        Weapon weapon = null;
        Armor armor = null;
        Artifact artifact = null;
        Potion potion = null;
        int gold = 0;
        String loot = "";
        int roll = (int) (Math.random() * 100);

        if(isBetween(roll, 0, 25)){
            //little gold
            gold = 25 * level + (int)(Math.random() * 25) + 25;
            loot = "You found a little gold. " + gold + " gold.";
        } else if(isBetween(roll, 26, 35)){
            //medium gold
            gold = 45 * level + (int)(Math.random() * 45 + 45);
            loot = "You found a medium amount of gold! " + gold + " gold!";
        } else if(isBetween(roll, 36, 40)){
            //lots of gold
            gold = 85 * level + (int)(Math.random() * 85 + 85);
            loot = "You found a lot of gold! " + gold + " gold!";
        } else if(isBetween(roll, 41, 55)){
            potion = new Potion("Heal Potion", level * 6 + ((int)(Math.random()*3) *level), level * 10);
            potion.setIconPath(ap.healthPotion);
            loot = "You found a health potion!";
        } else if(isBetween(roll, 56, 70)){

            //other potion --> not made yet
            potion = makeOtherPotion(level);
            loot = "You found a " + potion.getType() + "!";
        } else if(isBetween(roll, 71, 80)){
            weapon = wg.generateWeapons(level,1).get(0);
            loot = "You found a weapon: " + weapon.getName();
        } else if(isBetween(roll, 81, 90)){
            armor = ag.generateArmor(level, 1).get(0);
            loot = "You found armor: " + armor.getName();
        } else if(isBetween(roll, 91, 100)){
            artifact = artg.generateArtifact(level, 1).get(0);
            loot = "You found an artifact: " + artifact.getName();
        } else {
            loot = "Error in ChestLoot.java";
        }
        roll = (int) (Math.random() * 100);
        if (roll > 7){
            MysticInterface mystic = mg.getNewMystic(hero.getMystics(), "common");
            return new Loot(weapon, armor, artifact, potion, gold, loot, mystic);
        }
        return new Loot(weapon, armor, artifact, potion, gold, loot, null);
    }

    public Loot generateBossLoot(Hero hero){
        wg = new WeaponGenerator(hero);
        int level = hero.getLevel();
        Weapon weapon = null;
        Armor armor = null;
        Artifact artifact = null;
        Potion potion = null;
        int gold = 0;
        String loot = "";
        int roll = (int) (Math.random() * 100);
        if(roll%3 == 0){
            weapon = wg.generateWeapons(level + 3,1).get(0);
            loot = "You found a weapon: " + weapon.getName() + ", ";
        } else if(roll%3 == 1){
            armor = ag.generateArmor(level + 3, 1).get(0);
            loot = "You found armor: " + armor.getName() + ", ";
        } else {
            artifact = artg.generateArtifact(level + 3, 1).get(0);
            loot = "You found an artifact: " + artifact.getName() + ", ";
        }
        gold = level * 50 + (int)(Math.random() * 100 + 100);
        loot += "and " + gold + " gold, and ";
        potion = makeOtherPotion(level);
        loot += "a " + potion.getType() + "!";
        MysticInterface mystic = mg.getNewMystic(hero.getMystics(), "rare");
        return new Loot(weapon, armor, artifact, potion, gold, loot, mystic);
    }

    public static boolean isBetween(int x, int lower, int upper) {
        return lower <= x && x <= upper;
    }

    public Potion makeOtherPotion(int level){
        PotionGenerator pg = new PotionGenerator();
        int potionRoll = (int)(Math.random() * 3);
        switch (potionRoll){
            case 0:
                return pg.makeXPotion(level * 50, level * 15);
            case 1:
                return pg.makeDevilPotion(0, level * 25);
                case 2:
                    return pg.makeBloodPotion(0, level * 20);
            default:
                return new Potion("Heal Potion", level * 6 + ((int)(Math.random()*3) *level), level * 10);
        }
    }

}
