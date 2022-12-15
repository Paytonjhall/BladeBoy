package Character.Town;
import Character.*;

import java.util.ArrayList;
import java.util.List;

public class PotionGenerator {
    public List<Potion> generatePotion(int number){
       List<Potion> potions = new ArrayList<Potion>();
        for(int i = 0; i < number; i++){
            potions.add(new Potion("Heal Potion", 40, 100));
        }
        return potions;
    }
}
