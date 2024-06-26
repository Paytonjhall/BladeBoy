package Character.Town;
import Character.*;
import Character.Equipment.Weapon;
import Character.Class;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WeaponGenerator {

    Hero hero;

    // Rank1 artifacts = 5, Rank2 artifacts = 10, Rank3 artifacts = 15
    // Value is equal

    String[] bowNames = new String[]{"Bow", "Longbow", "Shortbow", "Crossbow", "Recurve Bow", "Compound Bow", "Composite Bow", "Reflex Bow", "Recurve Crossbow", "Compound Crossbow", "Composite Crossbow", "Reflex Crossbow"};
    String[] staffNames = new String[]{"Staff", "Rod", "Wand", "Scepter", "Cane", "Pole", "Pike", "Lance", "Glaive", "Guisarme", "Quarterstaff"};
    String[] axeNames = new String[]{"Axe", "Hatchet", "Tomahawk", "Battle Axe", "Hand Axe", "War Axe", "Great Axe"};
    String[] swordNames = new String[]{"Sword", "Blade", "Dirk","Axe", "Knife", "Razor","Battle Axe", "Hand Axe", "Rapier", "Edge", "Broadsword", "Knife", "Dagger", "Scimitar", "Longsword", "Shortsword", "Saber", "Cutlass", "Falchion", "Claymore", "Flamberge", "Zweihander", "Mace", "Morning Star", "War Hammer", "Club", "Flail", "Maul", "Spear", "Pike", "Halberd", "Trident", "Lance", "Javelin", "Glaive", "Guisarme", "Quarterstaff", "Bastard Sword", "Scythe", "War Scythe", "Great Sword", "Great Axe", "Great Mace", "Great Hammer"};
    String[] adjNames = new String[]{"Quick", "Heavy", "Fine", "Chipped", "Robust", "Strong", "Dark", "Steel", "Iron", "Bronze", "Silver", "Gold", "Platinum", "Diamond", "Ruby", "Emerald", "Sapphire", "Amethyst", "Opal", "Topaz", "Onyx", "Obsidian", "Copper", "Tin", "Lead", "Zinc", "Aluminum", "Titanium", "Mithril", "Adamantium", "Tungsten", "Thorium", "Neptunium", "Polonium", "Bismuth", "Antimony", "Selenium", "Tellurium", "Krypton", "Argon", "Helium", "Neon", "Lithium", "Beryllium", "Carbon", "Nitrogen","Magnesium", "Aluminum","Phosphorus", "Chlorine", "Argon","Titanium", "Vanadium", "Chromium", "Manganese", "Iron", "Cobalt", "Nickel", "Copper", "Zinc", "Gallium", "Germanium", "Bromine", "Rubidium", "Strontium", "Yttrium", "Zirconium", "Niobium", "Molybdenum", "Technetium", "Ruthenium", "Rhodium", "Palladium", "Silver", "Cadmium", "Indium", "Tin", "Antimony", "Tellurium", "Cesium", "Barium", "Lanthanum", "Cerium", "Praseodymium", "Neodymium", "Promethium", "Samarium", "Europium", "Gadolinium", "Terbium", "Dysprosium", "Holmium", "Erbium", "Thulium", "Ytterbium"};
    public WeaponGenerator(Hero hero) {
        this.hero = hero;
    }

    public List<Weapon> generateWeapons(int rank, int count){
        List<Weapon> weapons = new ArrayList<>();

        for(int i = 0; i < count; i++){
            Weapon weapon = new Weapon(generateWeaponName());
            if(weapon.getName().contains("sword") || weapon.getName().contains("Sword") || weapon.getName().contains("Rapier") || weapon.getName().contains("Scimitar") || weapon.getName().contains("Saber") || weapon.getName().contains("Claymore") || weapon.getName().contains("Zweihander")) weapon.setType("Sword");
            else if(weapon.getName().contains("Bow") || weapon.getName().contains("bow") || weapon.getName().contains("Crossbow") || weapon.getName().contains("crossbow")) weapon.setType("Bow");
            else if(weapon.getName().contains("dagger") || weapon.getName().contains("Dagger") || weapon.getName().contains("Knife") || weapon.getName().contains("Razor")) weapon.setType("Dagger");
            else if(weapon.getName().contains("axe") || weapon.getName().contains("Axe") || weapon.getName().contains("Mace") || weapon.getName().contains("Flail") || weapon.getName().contains("Club") || weapon.getName().contains("Hammer") || weapon.getName().contains("Maul") || weapon.getName().contains("Morning Star") || weapon.getName().contains("Tomahawk") || weapon.getName().contains("Hatchet")) weapon.setType("Axe");
            else if(weapon.getName().contains("spear") || weapon.getName().contains("Spear") || weapon.getName().contains("Pike") || weapon.getName().contains("Halberd") || weapon.getName().contains("Trident") || weapon.getName().contains("Lance") || weapon.getName().contains("Guisarme") || weapon.getName().contains("Quarterstaff") || weapon.getName().contains("Scythe") || weapon.getName().contains("Javelin")) weapon.setType("Spear");
            else if(weapon.getName().contains("staff") || weapon.getName().contains("Staff") || weapon.getName().contains("Rod") || weapon.getName().contains("Wand") || weapon.getName().contains("Scepter") || weapon.getName().contains("Cane") || weapon.getName().contains("Pole") || weapon.getName().contains("Pike") || weapon.getName().contains("Lance") || weapon.getName().contains("Glaive") || weapon.getName().contains("Guisarme") || weapon.getName().contains("Quarterstaff")) weapon.setType("Staff");
            else weapon.setType(weapon.setRandomType());
            int wRank = calculateWeaponRank(rank);
            if(wRank<=0){
                wRank = 1;
            }
            int damage = (getRnd().nextInt(4)+1) * (wRank+1);
            weapon.setWeaponDamage(damage + (wRank * getRnd().nextInt(wRank+1)));
            weapon.setDescription("A weapon of rank " + wRank + " made at the blacksmith's shop.");
            weapon.setValue((int)Math.abs(wRank * wRank * (wRank + wRank)* (getRnd().nextInt(15))) + (damage * (getRnd().nextInt(6)+14)));
            int num = getRnd().nextInt(9) +1;
            weapon.setPath();
            weapons.add(weapon);
        }
        return weapons;
    }


    public String generateWeaponName(){
    Class heroClass = hero.getHeroClass();
    // System.out.println(heroClass.getClassType());
        return switch (heroClass.getClassType()) {
            case "Mage" -> generateAdj() + " " + staffNames[getRnd().nextInt(staffNames.length)];
            case "Barbarian" -> generateAdj() + " " + axeNames[getRnd().nextInt(axeNames.length)];
            case "Knight" -> generateAdj() + " " + swordNames[getRnd().nextInt(swordNames.length)];
            case "Archer" -> generateAdj() + " " + bowNames[getRnd().nextInt(bowNames.length)];
            default -> generateAdj() + " " +  swordNames[getRnd().nextInt(swordNames.length)];
        };
    }

    public String generateAdj(){
        return adjNames[getRnd().nextInt(adjNames.length)];
    }

    Random getRnd(){
        return new Random();
    }

    public int calculateWeaponRank(int rank){
        int rankCheck = getRnd().nextInt(10) +  1;
        if(rankCheck < 2){
            return --rank;
    } else if(rankCheck >= 9){
            return ++rank;
        } else {
            return rank;
        }
    }

}
