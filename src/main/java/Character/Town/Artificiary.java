package Character.Town;
import Character.*;
import Character.Equipment.Artifact;
import Game.Output;
import Game.UserInput;

import java.util.ArrayList;
import java.util.List;

public class Artificiary {
  Output output = new Output();
  UserInput userInput = new UserInput();
  ArtifactGenerator ag= new ArtifactGenerator();
  List<Artifact> artifacts= new ArrayList<>();
  boolean resetAvailable = true;
  public Artificiary() {
  }

  public Hero visitArtificiary(Hero hero){
    System.out.println("Welcome to the Blacksmith!");
    System.out.println("Here are todays deals:");

    userInput.sleep(2000);
    if(resetAvailable){
      artifacts= ag.generateArtifact(hero.getLevel()/5, 5);
      resetAvailable = false;
    }

    int count = 1;
    System.out.println("[0]: Exit");
    for(Artifact artifact: artifacts){
      System.out.println("["+count+"]: " + artifact.toString());
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
      if (choice > 0 && choice <= artifacts.size()) {
        if (hero.getGold() >= artifacts.get(choice - 1).getValue()) {
          hero.setGold(hero.getGold() - artifacts.get(choice - 1).getValue());
          hero.addToBag(artifacts.get(choice - 1));
          System.out.println("You bought a " + artifacts.get(choice - 1).getName());
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

  public List<Artifact> getItems(Hero hero){
    if(artifacts.isEmpty()){
      artifacts= ag.generateArtifact(hero.getLevel()/5, 5);
    }
    return artifacts;
  }

  public boolean itemBuyable(Hero hero, Artifact artifact){
    if(hero.getGold() >= artifact.getValue()){
      return true;
    }
    return false;
  }

  public Hero buyItem(Hero hero, Artifact artifact){
    hero.setGold(hero.getGold() - artifact.getValue());
    hero.addToBag(artifact);
    System.out.println("You bought a " + artifact.getName());
    artifacts.remove(artifact);
    return hero;
  }

  public void removeArtifact(Artifact artifact){
    artifacts.remove(artifact);
  }

  public boolean isResetAvailable() {
    return resetAvailable;
  }

  public void setResetAvailable(boolean resetAvailable) {
    this.resetAvailable = resetAvailable;
  }
}
