package Character.Town;
import Character.*;
import Dungeon.Adventure;
import Game.*;

public class Town {

    Blacksmith blacksmith = new Blacksmith();
    Oracle oracle = new Oracle();
    Output output = new Output();
    UserInput userInput = new UserInput();
    Hero hero;
    Adventure adventure = new Adventure();

    public Town(Hero hero) {
        this.hero = hero;
        hero.heal(10000);
        System.out.println("You arrive at the town");
        System.out.println("You rest for the day, in the morning you get ready for your next adventure");
        userInput.sleep(2000);
        printOptions();
        System.out.print("Where would you like to go: ");
        getAction();
    }

    public Hero visitBlacksmith (Hero hero) {
        return blacksmith.visitBlacksmith(hero);
    }

    public Hero visitOracle (Hero hero) {return oracle.visitOracle(hero);}

    public Hero startAdventure (Hero hero) {
        return adventure.startAdventure(hero);
    }


    public void printOptions () {
        System.out.print("[1]: ");
        output.printRed("Blacksmith\n");
        System.out.print("[2]: ");
        output.printPurple("Store\n");
        System.out.print("[3]: ");
        output.printBlue("Oracle\n");
        System.out.print("[4]: ");
        output.printGreen("Equipment\n");
        System.out.print("[5]: ");
        output.printCyan("Character Stats\n");
        System.out.print("[6]: ");
        output.printYellow("Next Adventure\n");
    }

    public void getAction () {
        int choice = -1;
        while (choice != 6) {
            choice = userInput.getNumberInput();
            switch (choice) {
                case 1 -> hero = visitBlacksmith(hero);
                case 2 -> System.out.println("Store");
                case 3 -> hero = visitOracle(hero);
                case 4 -> System.out.println("Equipment");
                case 5 -> System.out.println("Character Stats");
                case 6 -> {
                    hero = startAdventure(hero);
                    returnToTown(hero);
                }
                default -> System.out.println("Invalid input");
            }

        }
    }

    public void resetTown(){
        this.blacksmith = new Blacksmith();
        this.oracle = new Oracle();
    }

    public void returnToTown(Hero hero){
        resetTown();
        this.hero = hero;
        hero.heal(10000);
        System.out.println("You arrive at the town");
        System.out.println("You rest for the day, in the morning you get ready for your next adventure");
        userInput.sleep(2000);
        printOptions();
        System.out.print("Where would you like to go: ");
        getAction();
    }
}
