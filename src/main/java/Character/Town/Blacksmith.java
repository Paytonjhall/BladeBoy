package Character.Town;
import Character.*;
import Game.Output;
import Game.UserInput;

import java.util.ArrayList;
import java.util.List;

public class Blacksmith {
    Output output = new Output();
    UserInput userInput = new UserInput();
    WeaponGenerator wg = new WeaponGenerator();
    List<Weapon> weapons = new ArrayList<Weapon>();
    boolean resetAvailable = true;
    public Blacksmith() {
    }

    public Hero visitBlacksmith(Hero hero){
        System.out.println("Welcome to the Blacksmith!");
        System.out.println("Here are todays deals:");

        userInput.sleep(2000);
        if(resetAvailable){
            weapons = wg.generateWeapons(hero.getLevel()/5, 5);
            resetAvailable = false;
        }

        int count = 1;
        System.out.println("[0]: Exit");
        for(Weapon weapon: weapons){
            System.out.println("["+count+"]: " + weapon.toString());
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
        if (choice > 0 && choice <= weapons.size()) {
                if (hero.getGold() >= weapons.get(choice - 1).getValue()) {
                    hero.setGold(hero.getGold() - weapons.get(choice - 1).getValue());
                    hero.addToBag(weapons.get(choice - 1));
                    System.out.println("You bought a " + weapons.get(choice - 1).getName());
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

    public List<Weapon> getItems(Hero hero){
        if(weapons.isEmpty()){
            weapons = wg.generateWeapons(hero.getLevel()/5, 5);
        }
        return weapons;
    }

    public boolean itemBuyable(Hero hero, Weapon weapon){
        if(hero.getGold() >= weapon.getValue()){
            return true;
        }
        return false;
    }

    public Hero buyItem(Hero hero, Weapon weapon){
            hero.setGold(hero.getGold() - weapon.getValue());
            hero.addToBag(weapon);
            System.out.println("You bought a " + weapon.getName());
            weapons.remove(weapon);
        return hero;
    }

    public void removeWeapon(Weapon weapon){
        weapons.remove(weapon);
    }

    public boolean isResetAvailable() {
        return resetAvailable;
    }

    public void setResetAvailable(boolean resetAvailable) {
        this.resetAvailable = resetAvailable;
    }
}
