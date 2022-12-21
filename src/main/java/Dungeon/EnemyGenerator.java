package Dungeon;


import java.util.Random;
import Character.*;

// This enemy Generator class is to be used with the adventure class in the dungeon package.
// It will create enemies based on the adventure count, not nesecarily based on the hero level.
public class EnemyGenerator {
    String[] monsterNames =	new String[]{ "angel", "brownie", "bugbear", "centaur", "chimera", "chupacabra", "cockatrice", "cyclops", "demon", "djinn", "dragon", "draugr", "dryad", "dwarf", "elemental", "elf", "fairy", "faun", "frost giant", "gargoyle", "genie", "ghast", "ghost", "ghoul", "giant", "gnome", "goblin", "golem", "gorgon", "gremlin", "griffon", "hag", "harpy", "hippogriff", "hobgoblin", "homonculus", "hydra", "imp", "incubus", "kappa", "kobold", "kraken", "lamassu", "leprechaun", "lich", "manticore", "mermaid", "merman", "minotaur", "mummy", "naga", "nix", "nymph", "ogre", "oni", "orc", "phoenix", "pixie", "poltergeist", "roc", "sasquatch", "satyr", "selkie", "siren", "spectre", "sphinx", "sprite", "succubus", "sylph", "thunderbird", "troll", "unicorn", "vampire", "valkyrie", "warg", "wendigo", "werewolf", "wight", "witch", "wraith", "wyvern", "yeti", "zombie"};
    String[] monsterAdjectives = new String[]{"Angry ", "Large ", "Evil ", "Vile ", "Demonic ", "Dubious ", "Devilish ", "Destructive ", "Wicked ", "Distasteful ", "Scaly ", "Looming ", "Ghastly ", "Horrific ", "Scary "};
    Random rnd = new Random();
    int level;
    public EnemyGenerator(int level) {
        this.level = level;
    }


    public Enemy generateEnemy(){
        int random = rnd.nextInt(10);
        int health =  random + 5 + (7 * level);
        int damage = (4 * level) + 5 - random/2;
        double armor = (.05 * level);
        int xp = (level * 15) + rnd.nextInt(15) + 15;
        int gold = (level * 12) + rnd.nextInt(20) + rnd.nextInt(5);
        boolean drop;
        int item = rnd.nextInt(100);
        if(item % 2 == 0){
            drop = true;

        } else {
            drop = false;
        }
        //TODO: add item drop here.
        return new Enemy(getName(), health, damage, armor, xp, null, gold);
    }

    public void resetLevel(int level){
        this.level = level;
    }

    public String getName(){

        String name = monsterAdjectives[rnd.nextInt(monsterAdjectives.length)];
        name += monsterNames[rnd.nextInt(monsterNames.length)];
        return name;
    }


}
