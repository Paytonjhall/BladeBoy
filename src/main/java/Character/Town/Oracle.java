package Character.Town;
import Character.*;
import Game.Output;

public class Oracle {
    Output output = new Output();
    public Oracle() {
    }
    Hero hero;
    public Hero visitOracle(Hero hero){
        this.hero = hero;
        System.out.print("Welcome to the "); output.printPurple("Oracle"); System.out.println("!");
        if(hero.skillPoints < 1) {
            System.out.println("You don't have any skill points. . .");
            System.out.println("But I'll still let you look at the possibilities");
        }
        else {
            System.out.println("You have " + hero.skillPoints + " skill points");
            System.out.println("Lets take a look at what you can upgrade");
        }
        System.out.println("Here are your stats:");
        return hero;
    }

    public void loadLevelUpgrades(){

    }
}
