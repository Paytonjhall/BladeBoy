package Character.Town;

import Game.Output;
import Game.UserInput;
import Character.*;
import java.util.ArrayList;
import java.util.List;

public class Store {
    Output output = new Output();
    UserInput userInput = new UserInput();
    List<Potion> potions = new ArrayList<Potion>();
    boolean resetAvailable = true;
    PotionGenerator pg = new PotionGenerator();


    public Hero visitStore(Hero hero) {
        System.out.println("Welcome to the Store!");
        System.out.println("Here are today's deals:");

        userInput.sleep(2000);
        if (resetAvailable) {
            potions = pg.generatePotion(5);
            resetAvailable = false;
        }

        int count = 1;

        int choice = -1;
        System.out.println("[0]: Exit");
        for (Potion potion : potions) {
            System.out.println("[" + count + "]: " + potion.toString());
            count++;
        }
        System.out.print("Your Gold: " );
        output.printYellow("" + hero.getGold() +"\n");
        while (true) {
            System.out.print("What items would you to buy: ");
            choice = userInput.getNumberInput();
            if (choice == 0) {
                return hero;
            }
            if (choice > 0 && choice <= potions.size()) {
                if (hero.getGold() >= potions.get(choice - 1).getValue()) {
                    hero.setGold(hero.getGold() - potions.get(choice - 1).getValue());
                    hero.addPotion(potions.get(choice - 1));
                    System.out.println("You bought a " + potions.get(choice - 1).toString());
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
