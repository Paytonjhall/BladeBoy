package Character.Town;
import Character.*;
import Dungeon.Adventure;
import Game.*;

public class Town {

    Blacksmith blacksmith = new Blacksmith();
    Oracle oracle = new Oracle();
    Store store = new Store();
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

    public Hero visitStore (Hero hero) {return store.visitStore(hero);}

    public void saveGame (Hero hero) {
        Save save = new Save();
        save.saveGame(hero);
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
        System.out.print("[7]: ");
        output.printBold("Save Game\n");
    }

    public void getAction () {
        int choice = -1;
        while (choice != 6) {
            choice = userInput.getNumberInput();
            switch (choice) {
                case 1 -> hero = visitBlacksmith(hero);
                case 2 -> hero = visitStore(hero);
                case 3 -> hero = visitOracle(hero);
                case 4 -> hero.organizeBag();
                case 5 -> hero.heroStatus();
                case 6 -> {
                    hero = startAdventure(hero);
                    returnToTown(hero);
                }
                case 7 -> saveGame(hero);
                default -> System.out.println("Invalid input");
            }

        }
    }



    public void resetTown(){
        this.blacksmith = new Blacksmith();
        this.oracle = new Oracle();
        this.store = new Store();
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
