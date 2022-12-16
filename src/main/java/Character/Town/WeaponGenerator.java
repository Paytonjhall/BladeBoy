package Character.Town;
import Character.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

public class WeaponGenerator {

    Hero hero;

    // Rank1 weapons = 5, Rank2 weapons = 10, Rank3 weapons = 15
    // Value is equal

    String[] swordNames = new String[]{"Sword", "Blade", "Dirk","Axe", "Battle Axe", "Hand Axe", "Rapier", "Edge", "Broadsword", "Knife", "Dagger", "Scimitar", "Longsword", "Shortsword", "Saber", "Cutlass", "Falchion", "Claymore", "Flamberge", "Zweihander", "Mace", "Morning Star", "War Hammer", "Club", "Flail", "Maul", "Spear", "Pike", "Halberd", "Trident", "Lance", "Javelin", "Glaive", "Guisarme", "Quarterstaff", "Bastard Sword", "Scythe", "War Scythe", "Great Sword", "Great Axe", "Great Mace", "Great Hammer", "Great Flail", "Great Club", "Great Spear", "Great Pike", "Great Halberd", "Great Glaive", "Great Guisarme", "Great Scythe", "Great War Scythe", "Great Sword", "Great Axe", "Great Mace", "Great Hammer", "Great Flail", "Great Club", "Great Spear", "Great Pike", "Great Halberd", "Great Glaive", "Great Guisarme", "Great Scythe", "Great War Scythe", "Great Sword", "Great Axe", "Great Mace", "Great Hammer", "Great Flail", "Great Club", "Great Spear", "Great Pike", "Great Halberd", "Great Glaive", "Great Guisarme", "Great Scythe", "Great War Scythe", "Great Sword", "Great Axe", "Great Mace", "Great Hammer", "Great Flail", "Great Club", "Great Spear", "Great Pike", "Great Halberd", "Great Glaive", "Great Guisarme", "Great Scythe", "Great War Scythe", "Great Sword", "Great Axe", "Great Mace", "Great Hammer", "Great Flail", "Great Club", "Great Spear", "Great Pike", "Great Halberd", "Great Glaive", "Great Guisarme", "Great Scythe", "Great War Scythe", "Great Sword", "Great Axe", "Great Mace", "Great Hammer", "Great Flail", "Great Club", "Great Spear", "Great Pike", "Great Halberd", "Great Glaive", "Great Guisarme", "Great Scythe", "Great War Scythe", "Great Sword", "Great Axe", "Great Mace", "Great Hammer", "Great Flail", "Great Club"};
    String[] adjNames = new String[]{"Quick", "Heavy", "Fine", "Chipped", "Robust", "Strong", "Dark", "Steel", "Iron", "Bronze", "Silver", "Gold", "Platinum", "Diamond", "Ruby", "Emerald", "Sapphire", "Amethyst", "Opal", "Topaz", "Onyx", "Obsidian", "Copper", "Tin", "Lead", "Zinc", "Aluminum", "Titanium", "Mithril", "Adamantium", "Tungsten", "Uranium", "Plutonium", "Thorium", "Neptunium", "Radium", "Polonium", "Bismuth", "Antimony", "Arsenic", "Selenium", "Tellurium", "Iodine", "Xenon", "Krypton", "Argon", "Helium", "Neon", "Oxygen", "Nitrogen", "Carbon", "Hydrogen", "Lithium", "Beryllium", "Boron", "Carbon", "Nitrogen", "Oxygen", "Fluorine", "Neon", "Sodium", "Magnesium", "Aluminum", "Silicon", "Phosphorus", "Sulfur", "Chlorine", "Argon", "Potassium", "Calcium", "Scandium", "Titanium", "Vanadium", "Chromium", "Manganese", "Iron", "Cobalt", "Nickel", "Copper", "Zinc", "Gallium", "Germanium", "Arsenic", "Selenium", "Bromine", "Krypton", "Rubidium", "Strontium", "Yttrium", "Zirconium", "Niobium", "Molybdenum", "Technetium", "Ruthenium", "Rhodium", "Palladium", "Silver", "Cadmium", "Indium", "Tin", "Antimony", "Tellurium", "Iodine", "Xenon", "Cesium", "Barium", "Lanthanum", "Cerium", "Praseodymium", "Neodymium", "Promethium", "Samarium", "Europium", "Gadolinium", "Terbium", "Dysprosium", "Holmium", "Erbium", "Thulium", "Ytterbium"};
    public WeaponGenerator(Hero hero) {
        this.hero = hero;
    }

    public WeaponGenerator() {
    }


    public List<Weapon> generateWeapons(int rank, int count){
        List<Weapon> weapons = new ArrayList<>();

        for(int i = 0; i < count; i++){
            Weapon weapon = new Weapon(generateWeaponName());
            int wRank = calculateWeaponRank(rank);
            if(wRank<=0){
                wRank = 1;
            }
            int damage = (getRnd().nextInt(4)+1) * (wRank+1);
            weapon.setWeaponDamage(damage + (wRank * getRnd().nextInt(wRank+1)));
            weapon.setDescription("A weapon of rank " + wRank + " made at the blacksmith's shop.");
            weapon.setValue((int)Math.abs(wRank * wRank * (wRank + wRank)* (getRnd().nextInt(15))) + (damage * (getRnd().nextInt(6)+14)));
            weapons.add(weapon);
        }



        return weapons;
    }


    public String generateWeaponName(){
        return generateAdj() + " " + generateWeaponNoun();
    }

    public String generateAdj(){
        return adjNames[getRnd().nextInt(adjNames.length)];
    }

    public String generateWeaponNoun(){
        return swordNames[getRnd().nextInt(swordNames.length)];
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
