package Character.Town;
import Character.*;
import Game.Output;
import Game.UserInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Oracle {
    UserInput userInput = new UserInput();
    Output output = new Output();
    public Oracle() {
    }
    Hero hero;
    public Hero visitOracle(Hero hero)  {
        this.hero = hero;
        if(hero.skillPoints == null) return hero;
        int count = 0;
        for(int i = 0; i < hero.skillPoints.size(); i++){
            if(!hero.skillPoints.get(i).isUsed()) count++;
        }
        System.out.print("Welcome to the "); output.printPurple("Oracle"); System.out.println("!");
        if(count < 1) {
            System.out.println("You don't have any skill points. . .");
            System.out.println("Come back when you have more skill points");
        }
        else {
            System.out.println("You have " + count + " skill points");
            System.out.println("Lets take a look at what you can upgrade");
        }
        for(int i = 0; i < hero.skillPoints.size(); i++){
            if(!hero.skillPoints.get(i).isUsed()) {
                loadLevelUpgrades(hero.skillPoints.get(i).getLevel());
                hero.skillPoints.get(i).setUsed(true);
                userInput.sleep(2500);
            }
        }
        System.out.println("Thank you for visiting the oracle!");
        return hero;
    }

    public void loadLevelUpgrades(int level){
        System.out.println("Select a mystic for your skill point : (" + level + ")");
        List<Mystic> mystics = new ArrayList<Mystic>();
        Mystic mystic1 = new Mystic(level);
        Mystic mystic2 = new Mystic(level);
        Mystic mystic3 = new Mystic(level);
        mystics.add(mystic1);
        mystics.add(mystic2);
        mystics.add(mystic3);
        int count = 1;
        for(Mystic mystic: mystics){
            mystic.createMystic();
            System.out.println("[" + count + "]: " + mystic.toString());
            count++;
        }
        System.out.println("");
        int choice = userInput.getNumberInput();
        if(choice > 0 && choice <= mystics.size()){
        hero.addMystic(mystics.get(choice-1));
            System.out.println(" ---  You have chosen " + mystics.get(choice-1).toString() + " --- ");
            if(Objects.equals(mystics.get(choice - 1).getBuff(), "Health")) {
                hero.setMaxHealth(hero.getMaxHealth() + (int)mystics.get(choice - 1).getAmplifier());
                hero.setHealth(hero.getMaxHealth());
            }
        }
        else{
            System.out.println("Invalid input");
        }
    }
}
