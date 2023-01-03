package Character.Town;

import Game.Output;
import Game.UserInput;
import Character.*;
import java.util.ArrayList;
import java.util.List;

public class Armory {
  Output output = new Output();
  UserInput userInput = new UserInput();
  ArmorGenerator ag = new ArmorGenerator();
  List<Armor> armors = new ArrayList<Armor>();
  boolean resetAvailable = true;
  public Armory() {
  }

  public Hero visitArmory(Hero hero){
    System.out.println("Welcome to the Armory!");
    System.out.println("Here are todays deals:");

    userInput.sleep(2000);
    if(resetAvailable){
      armors = ag.generateArmor(hero.getLevel()/5, 5);
      resetAvailable = false;
    }

    int count = 1;
    System.out.println("[0]: Exit");
    for(Armor armor: armors){
      System.out.println("["+count+"]: " + armor.toString());
      count++;
    }
    int choice;
    System.out.print("Your Gold: " );
    output.printYellow("" + hero.getGold() +"\n");
    while(true) {
      System.out.print("What items would you to buy: ");
      choice = userInput.getNumberInput();
      if(choice == 0){
        return hero;
      }
      if (choice > 0 && choice <= armors.size()) {
        if (hero.getGold() >= armors.get(choice - 1).getValue()) {
          hero.setGold(hero.getGold() - armors.get(choice - 1).getValue());
          hero.addToBag(armors.get(choice - 1));
          System.out.println("You bought a " + armors.get(choice - 1).getName());
          userInput.sleep(2000);
        } else {
          System.out.println("You don't have enough gold");
          userInput.sleep(2000);
        }
      } else {
        System.out.println("Invalid input");
        userInput.sleep(2000);
      }
    }

  }

  public boolean isResetAvailable() {
    return resetAvailable;
  }

  public void setResetAvailable(boolean resetAvailable) {
    this.resetAvailable = resetAvailable;
  }
}
