package Character;
import Character.Abilities.Mystic;
import Character.Equipment.*;
import Dungeon.Loot;
import Game.*;
import Character.Mystics.MysticInterface;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Hero {
    AssetPath ap;
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
    String username = " ";
    public boolean inDungeon = false;
    public boolean finishedCombat = false;
    public boolean inCombat = false;
    public boolean inTown = false;
    public int x = 0;
    public int y = 0;

    String IconString = null;
    Class heroClass;

    public boolean visitedBlacksmith;
    public boolean visitedArmory;
    public boolean visitedArtificiary;


    public Class getHeroClass() {
        if (heroClass == null) {
            heroClass = new Class("Knight", "Physical");
        }
        return heroClass;
    }

    public void setHeroClass(Class heroClass) {
        this.heroClass = heroClass;
        setHeroIcon();
    }

    public List<SkillPoints> skillPoints = new ArrayList<>();
    List<MysticInterface> mystics = new ArrayList<>();
    PotionBag potionBag = new PotionBag();
    UserInput input = new UserInput();

    Output output = new Output();
    Sound sound = new Sound();
    public boolean wait = false;

    public Hero(Armor armor, Weapon weapon, Artifact artifact, int health, int nextLevelXp, int xp, int level, int gold, List<MysticInterface> mystics) {
        equipArmor(armor);
        this.ap = new AssetPath();
        this.weapon = weapon;
        this.health = health;
        this.maxHealth = health;
        this.artifact = artifact;
        this.xp = xp;
        this.level = level;
        this.nextLevelXp = nextLevelXp;
        this.gold = gold;
        if(mystics != null) {
            this.mystics = mystics;
        } else {
            this.mystics = new ArrayList<>();
        }
    }

    //For testing purposes
    public Hero(int skillpoints){
        for(int i = 1; i < skillpoints + 1; i++){
            skillPoints.add(new SkillPoints(i, false));
        }
    }

    public Hero(HeroSaveAdapter newHero){
        this.ap = new AssetPath();
        Bag = new ArrayList<ItemInterface>();
        this.armor = newHero.armor;
        this.weapon = newHero.weapon;
        this.artifact = newHero.artifact;
        Bag.addAll(newHero.artifacts);
        Bag.addAll(newHero.armors);
        Bag.addAll(newHero.weapons);
        this.mystics = newHero.mystics;
        this.health = newHero.health;
        this.maxHealth = newHero.maxHealth;
        this.xp = newHero.xp;
        this.level = newHero.level;
        this.nextLevelXp = newHero.nextLevelXp;
        this.gold = newHero.gold;
        this.dungeonCount = newHero.dungeonCount;
        this.username = newHero.username;
        this.skillPoints = newHero.skillPoints;
        this.potionBag = newHero.potionBag;
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
        output.printPurple("Mystics: ");
        for (MysticInterface mystic : mystics) {System.out.println(mystic.nameString());}
        System.out.println();
    }

    public boolean takeDamage(int damage){
        if(damage <= 0) damage = 0;
        health -= damage;
        if(health<=0){
            System.out.println("You have died");
            heroDeath();
            return true;
        }
        return false;
    }

    public void addGold(int gold){
        sound.gainMoneySound();
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

    public String getIconString() {
        if (IconString == null) setHeroIcon();
        return IconString;
    }

    public void setIconString(String iconString) {
        IconString = iconString;
    }

    public void heal (int health){
        this.health += health;
        if(this.health > this.maxHealth){
            this.health = this.maxHealth;
        }
    }

    public void levelUp(){
        sound.levelUpSound();
        skillPoints.add(new SkillPoints(level, false));
        level++;
        nextLevelXp = (int) (nextLevelXp * 1.25);
        System.out.println("You leveled up: " + level);
        System.out.println("You heal 25% of your health on level up, gain 10 hp, and are given a skill point!");
        maxHealth += 10;
        heal((int)maxHealth/4);
    }

    public void addToBag(ItemInterface item){
        if(Bag == null){
            Bag = new ArrayList<ItemInterface>();
        }
        Bag.add(item);
    }

    public int getProtection(){
        if(armor != null)return armor.getProtection();
        else return 0;
    }

    public void equipWeapon(Weapon weapon){
        sound.equipItemSound();
        if (this.weapon != null){
            addToBag(this.weapon);
        }
        if(Bag!=null)Bag.remove(weapon);
        this.weapon = weapon;
    }

    public void equipArtifact(Artifact artifact){
        sound.equipItemSound();
        if (this.artifact != null){
            addToBag(this.artifact);
        }
        if(Bag!=null)Bag.remove(artifact);
        this.artifact = artifact;
    }

    public void equipArmor(Armor armor){
        sound.equipItemSound();
        if (this.armor != null){
            maxHealth -= this.armor.getHealthIncrease();
            addToBag(this.armor);
        }
        if(Bag!=null)Bag.remove(armor);
        this.armor = armor;
        maxHealth += armor.getHealthIncrease();
    }

    public void heroDeath(){
        sound.deathSound();
        System.out.println("You have died");
        input.sleep(1000);
        System.out.println("Game Over");
        input.sleep(1000);
        System.out.println("Your save file has been deleted.");
        input.sleep(1000);
        System.out.println("This is how life is. I'm sorry.");
        input.sleep(1000);
        System.out.println("We will print your stats for you to see how you did.");
        input.sleep(1000);
        System.out.println();
        // TODO: Delete save file
        System.exit(0);
    }

    public String toString(){
        String heroString = "";
        heroString += "Hero: " + "\n";
        heroString += "Health: " + maxHealth + "\n";
        heroString += "XP: " + xp + "\n";
        heroString += "Level: " + level + "\n";
        heroString += "Gold: " + gold + "\n";
        heroString += "Weapon: " + weapon.getName() + "\n";
        heroString += "Armor: " + armor.getName() + "\n";
        heroString += "Artifact: " + artifact.getName() + "\n";
        heroString += "Mystics: " + mystics.toString() + "\n";
        return heroString;
    }

    public void usePotion(Potion potion){

        switch (potion.getType()) {
            case "Heal Potion" -> heal(potion.getEffect());
            case "XP Potion" -> addXp(potion.getEffect());
            case "Devil Potion" -> {
                health = 1;
                addXp(nextLevelXp);
            }
            case "Blood Potion" -> heal((int)Math.pow(((double)(maxHealth - health)/16), 2) ); //divide by ten then square.
            case "Regen Potion" -> System.out.println("Need to add hero active effects.");
            case "Luck Potion" -> System.out.println("Need to add hero active effects.");
            case "Gods Strength Potion" -> System.out.println("Not implemented yet");
            case "Defense Potion" -> System.out.println("Not implemented");
            case "Survival Potion" -> System.out.println("Needs to be implemented");
            case "Double Tap Potion" -> System.out.println("Need to add double tap potion");
            case "Scavenge Potion" -> System.out.println("Need to add scavenge");
        }
        sound.drinkSound();
        potionBag.removePotion(potion);

    }

    public void organizeBag() {
        List<Weapon> weapons = new ArrayList<>();
        List<Armor> armors = new ArrayList<>();
        List<Artifact> artifacts = new ArrayList<>();
        if (Bag == null || Bag.isEmpty()) {
            System.out.println("Your bag is empty");
            return;
        }
        for (ItemInterface itemInterface : Bag) {
            if (itemInterface.getClass() == Weapon.class) {
                weapons.add((Weapon) itemInterface);
            } else if (itemInterface.getClass() == Armor.class) {
                armors.add((Armor) itemInterface);
            } else if (itemInterface.getClass() == Artifact.class) {
                artifacts.add((Artifact) itemInterface);
            }
        }

        System.out.println(("[0] Exit"));
        System.out.println(("[1] Weapons"));
        System.out.println(("[2] Armors"));
        System.out.println(("[3] Artifacts"));
        System.out.print("Hero: ");
        int choice = input.getNumberInput();
        switch (choice) {
            case 1 -> {
                System.out.println("Weapons: ");
                System.out.print("Current Weapon: ");
                if(weapon != null) {
                    System.out.println(weapon.getName() + " - Damage: " + weapon.getWeaponDamage());
                } else {
                    System.out.println("None");
                }
                if (weapons.size() == 0 ){
                    System.out.println("You have no weapons in your bag");
                }
                for (int i = 0; i < weapons.size(); i++) {
                    System.out.println("[" + (i + 1) + "] " + weapons.get(i).getName() + " - Damage: " + weapons.get(i).getWeaponDamage());
                }
                System.out.println("[0] Exit");
                System.out.print("Hero: ");
                int weaponChoice = input.getNumberInput();
                if (weaponChoice == 0) {
                    organizeBag();
                }
                else if (weaponChoice > weapons.size()) {
                    System.out.println("Invalid input. Try again.");
                    input.clear();
                    organizeBag();
                }
                else {
                    equipWeapon(weapons.get(weaponChoice - 1));
                    Bag.remove(weapons.get(weaponChoice - 1));
                    System.out.println("You have equipped " + weapons.get(weaponChoice - 1).getName());
                    organizeBag();
                }

            }
            case 2 -> {
                System.out.println("Armors: ");
                System.out.print("Current Armor: ");
                if(armor != null) {
                    System.out.println(armor.getName() + " - Defense: " + armor.getArmorRating()*100 + "%");
                } else {
                    System.out.println("none");
                }
                if (armors.size() == 0) {
                    System.out.println("You have no armors in your bag");
                }
                for (int i = 0; i < armors.size(); i++) {
                    System.out.println("[" + (i + 1) + "] " + armors.get(i).getName() + " - Defense: " + armors.get(i).getArmorRating()*100 + "%" );
                }
                System.out.println("[0] Exit");
                System.out.print("Hero: ");
                int armorChoice = input.getNumberInput();
                if (armorChoice == 0) {
                    organizeBag();
                } else if (armorChoice > armors.size()) {
                    System.out.println("Invalid input. Try again.");
                    input.clear();
                    organizeBag();
                } else {
                    equipArmor(armors.get(armorChoice - 1));
                    Bag.remove(armors.get(armorChoice - 1));
                    System.out.println("You have equipped " + armors.get(armorChoice - 1).getName());
                    organizeBag();
                }
            }
            case 3 -> {
                System.out.println("Artifacts: ");
                System.out.print("Current Artifact: ");
                if(artifact != null) {
                    System.out.println(artifact.getName() + " - Effect: " + artifact.getType() + " : " + artifact.getAmplifier());
                } else {
                    System.out.println("none");
                }
                if (artifacts.size() == 0) {
                    System.out.println("You have no artifacts");
                }
                for (int i = 0; i < artifacts.size(); i++) {
                    System.out.println("[" + (i + 1) + "] " + artifacts.get(i).getName() + " - Effect: " + artifacts.get(i).getType() + " : " + artifacts.get(i).getAmplifier());
                }
                System.out.println("[0] Exit");
                System.out.print("Hero: ");
                int artifactChoice = input.getNumberInput();
                if (artifactChoice == 0) {
                    organizeBag();
                } else if (artifactChoice > artifacts.size()) {
                    System.out.println("Invalid input. Try again.");
                    input.clear();
                    organizeBag();
                } else {
                    equipArtifact(artifacts.get(artifactChoice - 1));
                    Bag.remove(artifacts.get(artifactChoice - 1));
                    System.out.println("You have equipped " + artifacts.get(artifactChoice - 1).getName());
                    organizeBag();
                }
            }
            case 0 -> {
                return;
            }
        }
    }

    public void addLoot(Loot loot){
        if(loot == null) return;
        if(loot.getWeapon() != null){
            Bag.add(loot.getWeapon());
        }
        if(loot.getArmor() != null){
            Bag.add(loot.getArmor());
        }
        if(loot.getArtifact() != null){
            Bag.add(loot.getArtifact());
        }
        if(loot.getPotion() != null && potionBag.getPotionCount() < 3){
            potionBag.addPotion(loot.getPotion());
        }
        gold += loot.getGold();
    }

    //GETTERS AND SETTERS

    public List<Weapon> getWeapons() {
        List<Weapon> weapons = new ArrayList<>();
        if(Bag == null) return weapons;
        for (ItemInterface itemInterface : Bag) {
            if (itemInterface.getClass() == Weapon.class) {
                weapons.add((Weapon) itemInterface);
            }
        }
        return weapons;
    }

    public List<Armor> getArmors() {
        List<Armor> armors = new ArrayList<>();
        if(Bag == null) return armors;
        for (ItemInterface itemInterface : Bag) {
            if (itemInterface.getClass() == Armor.class) {
                armors.add((Armor) itemInterface);
            }
        }
        return armors;
    }

    public List<Artifact> getArtifacts() {
        List<Artifact> artifacts = new ArrayList<>();
        if(Bag == null) return artifacts;
        for (ItemInterface itemInterface : Bag) {
            if (itemInterface.getClass() == Artifact.class) {
                artifacts.add((Artifact) itemInterface);
            }
        }
        return artifacts;
    }

    public List<ItemInterface> getBag() {
        return Bag;
    }

    public void setBag(List<ItemInterface> bag) {
        Bag = bag;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public List<SkillPoints> getSkillPoints() {
        return skillPoints;
    }

    public void setSkillPoints(List<SkillPoints> skillPoints) {
        this.skillPoints = skillPoints;
    }

    public void setMystics(List<MysticInterface> mystics) {
        this.mystics = mystics;
    }

    public PotionBag getPotionBag() {
        return potionBag;
    }

    public void setPotionBag(PotionBag potionBag) {
        this.potionBag = potionBag;
    }

    public void addPotion(Potion potion){
        potionBag.addPotion(potion);
    }

    public List<MysticInterface> getMystics() {
        return mystics;
    }

    public void addMystic (MysticInterface mystic){
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void addSkillPoint(int level, boolean used){
        skillPoints.add(new SkillPoints(level, used));
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

    public int getUnusedSkillPoints(){
        int count = 0;
        for(int i = 0; i < skillPoints.size(); i++){
            if(!skillPoints.get(i).isUsed()) count++;
        }
        return count;
    }

    public List<SkillPoints> getUnusedSkillPointsList(){
        List<SkillPoints> skillPointsList = new ArrayList<>();
        for(int i = 0; i < skillPoints.size(); i++){
            if(!skillPoints.get(i).isUsed()) skillPointsList.add(skillPoints.get(i));
        }
        return skillPointsList;
    }

    public void useSkillPoint(int level){
        for(int i = 0; i < skillPoints.size(); i++){
            if(skillPoints.get(i).getLevel() == level){
                skillPoints.get(i).setUsed(true);
            }
        }
    }

    public void setHeroIcon () {
        if (heroClass == null) {
            setIconString("Knight");
        } else {
            setIconString(heroClass.getClassType());
//            String iconPath = "";
//            switch (heroClass.getClassType()) {
//                case "Mage" -> iconPath = ap.mage;
//                case "Knight" -> iconPath = ap.knight;
//                case "Barbarian" -> iconPath = ap.barbarianIcon;
//                case "Archer" -> iconPath = ap.getIcon("Punk");
//            }
//            setIconString(iconPath);
        }
    }


}
