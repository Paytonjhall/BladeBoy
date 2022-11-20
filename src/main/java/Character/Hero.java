package Character;
import java.util.ArrayList;
import java.util.List;

public class Hero {
    Armor armor;
    Weapon weapon;
    Artifact artifact;
    int health;
    int maxHealth;
    int xp;
    int level;
    int nextLevelXp;
    int gold;
    List<Mystic> mystics = new ArrayList<Mystic>();
    PotionBag potionBag = new PotionBag();

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


    public void takeDamage(int damage){
        health -= damage;
        if(health<=0){
            System.out.println("You have died");
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
            levelUp();
            int tempXp = this.xp - nextLevelXp;
            this.xp = 0;
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
        level++;
        nextLevelXp = (int) (nextLevelXp * 1.25);
        System.out.println("You have leveled up to level " + level);
        System.out.println("Make level up system.");
    }


    //GETTERS AND SETTERS
    public List<Mystic> getMystics() {
        return mystics;
    }

    public void setMystics(List<Mystic> mystics) {
        this.mystics = mystics;
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
}
