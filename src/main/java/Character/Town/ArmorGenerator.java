package Character.Town;
import Character.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ArmorGenerator {
  String[] armorNames = new String[]{"Full Plated Suit","Leather Tunic", "Chain mail", "Plated Armor", "Clothing", "Torn Robes", "Scaled Armor", "Iron Plated Armor", "Steel Chain Mail", "Iron Chain Mail", "Worn Cloth", "Imperial Robes", "Dragon Leather Tunic", "Dragon Skin Hide", "Dragon Bone Chain Mail", "Dragon Plate Mail"};
  String[] armorAdjectives = new String[]{"Strong ", "Weak ", "Rusty ", "Durable ", "Shiny ", "Old ", "New ", "Weathered ", "Imperial ", "Well Made ", "Perfect ", "Grafted ", "Divine ", "Incredible ", " Badly Maintained ", "Futuristic " };


  public List<Armor> generateWeapons(int rank, int count){
    List<Armor> armors = new ArrayList<>();

    for(int i = 0; i < count; i++){
      Armor armor = new Armor(generateArmorName());
      int wRank = calculateArmorRank(rank);
      if(wRank<=0){
        wRank = 1;
      }
      int damage = (getRnd().nextInt(4)+1) * (wRank+1);
      armor.setArmorRating(damage + (wRank * getRnd().nextInt(10) + 50 * wRank));
      if(armor.getArmorRating() > 900){
        armor.setArmorRating(900);
      } else if(armor.getArmorRating() < 1){
        armor.setArmorRating(1);
      }
      armor.setHealthIncrease((int) (getRnd().nextInt(5) + 5 * (wRank + wRank/2)));
      armor.setDescription("A Armor of rank " + wRank + " made at the blacksmith's shop.");
      armor.setValue((int)Math.abs(wRank * wRank * (wRank + wRank)* (getRnd().nextInt(18))) + (damage * (getRnd().nextInt(10)+4)));
      armors.add(armor);
    }
    return armors;
  }


  public String generateArmorName(){
    return generateAdj() + " " + generateArmorNoun();
  }

  public String generateAdj(){return armorAdjectives[getRnd().nextInt(armorAdjectives.length)];}

  public String generateArmorNoun(){
    return armorNames[getRnd().nextInt(armorNames.length)];
  }

  Random getRnd(){
    return new Random();
  }

  public int calculateArmorRank(int rank){
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
