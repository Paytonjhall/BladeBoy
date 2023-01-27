package Character.Town;
import Character.Equipment.Potion;
import Game.AssetPath;

import java.util.ArrayList;
import java.util.List;

public class PotionGenerator {
    AssetPath ap = new AssetPath();
    public List<Potion> generatePotion(int number){
       List<Potion> potions = new ArrayList<Potion>();
        for(int i = 0; i < number; i++){
            potions.add(new Potion("Heal Potion", 40, 100));
        }
        return potions;
    }

    public Potion makeHealthPotion(int heal, int value){
        return new Potion("Heal Potion", heal, value, ap.healthPotion);
    }

    public Potion makeDevilPotion(int xp, int value){ // xp should be the xp to level up.
        return new Potion("Devil Potion", xp, value, ap.devilPotion);
    }

    public Potion makeXPotion(int xp, int value){ // xp in potion (level x 50)
        return new Potion("XP Potion", xp, value, ap.xpPotion);
    }

    public Potion makeBloodPotion(int missing, int value){ // missing health x .75
        return new Potion("Blood Potion", missing, value, ap.bloodPotion);
    }

    public Potion makeRegenPotion(int regen, int value){
        return new Potion("Regen Potion", regen, value, ap.regenPotion);
    }

    public Potion makeLuckPotion(int luck, int value){
        return new Potion("Luck Potion", 2, value, ap.luckPotion);
    }


}
