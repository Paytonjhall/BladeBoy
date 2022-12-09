package Character;
import Game.Output;
import Game.UserInput;

import java.util.ArrayList;
import java.util.List;

public class Hero {
    Armor armor;
    Weapon weapon;
    Artifact artifact;
    List<ItemInterface> Bag;
    int health;
    int maxHealth;
    int xp;
    int level;
    int nextLevelXp;
    int gold;
    // public int skillPoints=0;
    int dungeonCount = 0;
    public List<SkillPoints> skillPoints = new ArrayList<>();
    List<Mystic> mystics = new ArrayList<>();
    PotionBag potionBag = new PotionBag();
    UserInput input = new UserInput();

    Output output = new Output();

    public Hero(Armor armor, Weapon weapon, Artifact artifact, int health, int nextLevelXp, int xp, int level, int gold, List<Mystic> mystics) {
        this.armor = armor;
        this.weapon = weapon;
        this.health = health;
        this.maxHealth = health;
        this.artifact = artifact;
        this.xp = xp;
        this.level = level;
        this.nextLevelXp = nextLevelXp;
        this.gold = gold;
        this.mystics = mystics;

    }

    //For testing purposes
    public Hero(int skillpoints){
        for(int i = 0; i < skillpoints; i++){
            skillPoints.add(new SkillPoints(i, false));
        }
    }

    public void heroOptions(){
        boolean check = true;
        while(check){
            System.out.println("  [1]   Continue");
            System.out.println("  [2]   Hero Status");
            System.out.println("  [3]   Inventory");
            System.out.println("  [4]   Potion Bag");
            System.out.println("  [5]   Save Game");
            System.out.println("  [6]   Exit Game\n");
            System.out.print("Hero: ");

            int choice = input.getNumberInput();
            switch (choice) {
                case 1 -> {
                    check=false;
                    input.clear();
                }
                case 2 -> heroStatus();
                case 3 ->{
                    System.out.println("Inventory: needs to be implemented");
                    equipWeapon((Weapon)Bag.get(0));
                }
                case 4 -> System.out.println("Potion Bag: needs to be implemented");
                case 5 -> System.out.println("Saving game...");
                case 6 -> {
                    System.out.println("Exiting game...");
                    System.exit(0);
                }
                default -> {
                    System.out.println("Invalid input. Try again.");
                    input.clear();
                }
            }
        }

    }



    public void heroStatus() {
        input.clearScreen();
        output.printCyan("Hero Status\n");
        output.printRed("Health: ");
        System.out.println(health + "/" + maxHealth);
        output.printBlue("Level: ");
        System.out.println(level);
        output.printPurple("XP: ");
        System.out.println(xp + "/" + nextLevelXp);
        output.printYellow("Gold: ");
        System.out.println(gold);
        System.out.println("Weapon: " + weapon.getName());
        System.out.println("Armor: " + armor.getName());
        System.out.println("Artifact: " + artifact.getName());
        System.out.println("Mystics: ");
        System.out.println();
    }


    public void takeDamage(int damage){
        health -= damage;
        if(health<=0){
            System.out.println("You have died");
            System.exit(0);
        }
    }

    public void addGold(int gold){
        this.gold += gold;
    }

    public void addXp(int xp){
        System.out.println("You have gained " + xp + " xp");
        this.xp += xp;
        System.out.println("Xp:" + this.xp + "/" + this.nextLevelXp);
        if(this.xp >= nextLevelXp){
            int tempXp = this.xp - nextLevelXp;
            this.xp = 0;
            levelUp();
            addXp(tempXp);
        }
    }

    public void heal(int health){
        this.health += health;
        if(this.health > this.maxHealth){
            this.health = this.maxHealth;
        }
    }

    public void levelUp(){
        skillPoints.add(new SkillPoints(level, false));
        level++;
        nextLevelXp = (int) (nextLevelXp * 1.25);
        System.out.println("You leveled up: " + level);
        System.out.println("You heal 25% of your health on level up, gain 3 hp, and are given a skill point!");
        maxHealth += 3;
        heal((int)maxHealth/4);
    }

    public void addToBag(ItemInterface item){

        if(Bag == null){
            Bag = new ArrayList<ItemInterface>();
        }
        Bag.add(item);
    }

    public void equipWeapon(Weapon weapon){
        if (this.weapon != null){
            addToBag(this.weapon);
        }
        this.weapon = weapon;
    }


    //GETTERS AND SETTERS
    public List<Mystic> getMystics() {
        return mystics;
    }

    public void setMystics(List<Mystic> mystics) {
        this.mystics = mystics;
    }

    public void addMystic (Mystic mystic){
        mystics.add(mystic);
    }


    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold=gold;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Artifact getArtifact() {
        if(artifact == null) {
            return null;
        }
        return artifact;
    }

    public void setArtifact(Artifact artifact) {
        this.artifact = artifact;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getNextLevelXp() {
        return nextLevelXp;
    }

    public void setNextLevelXp(int nextLevelXp) {
        this.nextLevelXp = nextLevelXp;
    }

    public int getDungeonCount() {
        return dungeonCount;
    }

    public void setDungeonCount(int dungeonCount) {
        this.dungeonCount = dungeonCount;
    }


    public class SkillPoints {
        int level;
        boolean used;

        public SkillPoints(int level, boolean used) {
            this.level = level;
            this.used = used;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }
    }
}
