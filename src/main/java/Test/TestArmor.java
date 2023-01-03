package Test;
import Character.*;
import Character.Town.*;

import java.util.List;

public class TestArmor {
  public static void main(String[] args) {
    testArmor();
  }

  public static void testArmor(){
    ArmorGenerator wg = new ArmorGenerator();
    List<Armor> armors = wg.generateArmor(1, 10);

    for (Armor w : armors){
      System.out.println(w.toString());
    }

    armors = wg.generateArmor(3,10);
    System.out.println("\n\n");
    for (Armor w : armors){
      System.out.println(w.toString());
    }
  }
}

