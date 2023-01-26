package Dungeon;
import Character.*;
public class Loot {
    Weapon weapon = null;
    Armor armor = null;
    Artifact artifact = null;
    Potion potion = null;
    int gold = 0;
    String loot = "";
    public Loot(Weapon weapon, Armor armor, Artifact artifact, Potion potion, int gold, String loot){
        this.weapon = weapon;
        this.armor = armor;
        this.artifact = artifact;
        this.potion = potion;
        this.gold = gold;
        this.loot = loot;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public Artifact getArtifact() {
        return artifact;
    }

    public void setArtifact(Artifact artifact) {
        this.artifact = artifact;
    }

    public Potion getPotion() {
        return potion;
    }

    public void setPotion(Potion potion) {
        this.potion = potion;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public String getLoot() {
        return loot;
    }

    public void setLoot(String loot) {
        this.loot = loot;
    }

    public String getType(){
        if(weapon != null){
            return "Weapon";
        }else if(armor != null){
            return "Armor";
        }else if(artifact != null){
            return "Artifact";
        }else if(potion != null){
            return "Potion";
        }else{
            return "Gold";
        }
    }
}
