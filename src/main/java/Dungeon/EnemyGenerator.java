package Dungeon;


import java.util.List;
import java.util.Random;

import Character.Equipment.ItemInterface;
import Character.Equipment.Weapon;
import Character.Town.WeaponGenerator;
import Game.AssetPath;

// This enemy Generator class is to be used with the adventure class in the dungeon package.
// It will create enemies based on the adventure count, not nesecarily based on the hero level.
public class EnemyGenerator {
    WeaponGenerator weaponGenerator = new WeaponGenerator();
    AssetPath ap = new AssetPath();
    String[] monsterNames =	new String[]{"Goblin Soldier", "Goblin Archer", "Slime", "Golem", "Frog", "Wolf", "Bear", "Spider", "Monster", "Boar", "Bat"};
    String[] monsterAdjectives = new String[]{"Angry ", "Large ", "Evil ", "Vile ", "Demonic ", "Dubious ", "Devilish ", "Destructive ", "Wicked ", "Distasteful ", "Scaly ", "Looming ", "Ghastly ", "Horrific ", "Scary "};
    String[] bossNames =	new String[]{"Goblin Leader", "Slime Lord", "Monstrous Boar", "Barbarian Warlord"};

    Random rnd = new Random();
    int level;
    String iconPath = "";

    public EnemyGenerator(int level) {
        this.level = level;
    }


    public Enemy generateEnemy(){
        int random = rnd.nextInt(10);
        int health =  random + 5 + (7 * level);
        int damage = (4 * level) + 5 - random/2;
        double armor = (25 * level);
        if(armor > 900) armor = 900;
        int xp = (level * 15) + rnd.nextInt(15) + 15;
        int gold = (level * 12) + rnd.nextInt(20) + rnd.nextInt(5);
        boolean drop;
        int item = rnd.nextInt(100);
        if(item % 5 == 0){
            List<Weapon> weapons = weaponGenerator.generateWeapons(level/2,1);
            List<ItemInterface> drops = List.of(weapons.get(0));
            drop = true;
            return new Enemy(getName(), health, damage, armor, xp, drops, gold, iconPath);

        } else {
            drop = false;
            return new Enemy(getName(), health, damage, armor, xp, null, gold, iconPath);
        }
    }

    public Enemy generateBoss(){
        int random = rnd.nextInt(10) + 8;
        int health =  random + 10 + (7 * level);
        int damage = (6 * level) + 10 - random/2;
        double armor = (25 * level);
        if(armor > 900) armor = 900;
        int xp = (level * 25) + rnd.nextInt(25) + 25;
        int gold = (level * 22) + rnd.nextInt(30) + rnd.nextInt(17);
        boolean drop;
        int item = rnd.nextInt(100);
        if(item % 3 == 0){
            List<Weapon> weapons = weaponGenerator.generateWeapons(level+1,1);
            List<ItemInterface> drops = List.of(weapons.get(0));
            drop = true;
            return new Enemy(getBossName(), health, damage, armor, xp, drops, gold, iconPath);

        } else {
            drop = false;
            return new Enemy(getBossName(), health, damage, armor, xp, null, gold, iconPath);
        }
    }

    public void resetLevel(int level){
        this.level = level;
    }

    public String getName(){

        String name = monsterAdjectives[rnd.nextInt(monsterAdjectives.length)];
        name += monsterNames[rnd.nextInt(monsterNames.length)];
        if(name.contains("Goblin Soldier")) iconPath = ap.goblinSoldierIcon;
        else if(name.contains("Goblin Archer")) iconPath = ap.goblinArcherIcon;
        else if(name.contains("Slime")) iconPath = ap.slimeIcon;
        else if(name.contains("Golem")) iconPath = ap.golemIcon;
        else if(name.contains("Frog")) iconPath = ap.frogIcon;
        else if(name.contains("Wolf")) iconPath = ap.wolfIcon;
        else if(name.contains("Bear")) iconPath = ap.bearIcon;
        else if(name.contains("Spider")) iconPath = ap.spiderIcon;
        else if(name.contains("Monster")) iconPath = ap.monsterIcon;
        else if(name.contains("Boar")) iconPath = ap.boarIcon;
        else if(name.contains("Barbarian")) iconPath = ap.barbarianIcon;
        else if(name.contains("Bat")) iconPath = ap.batIcon;
        return name;
    }

    public String getBossName(){
        String name = monsterAdjectives[rnd.nextInt(monsterAdjectives.length)];
         name += bossNames[rnd.nextInt(bossNames.length)];
        if(name.contains("Goblin Leader")) iconPath = ap.goblinBossIcon;
        else if(name.contains("Slime Lord")) iconPath = ap.slimeBossIcon;
        else if(name.contains("Monstrous Boar")) iconPath = ap.boarBossIcon;
        else if(name.contains("Barbarian Warlord")) iconPath = ap.barbarianIcon;
        return name;
    }


}
