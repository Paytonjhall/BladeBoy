package Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Scanner;
import Character.*;
import view.GameView;

public class UserInput {
    Scanner scanner = new Scanner(System.in);
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


    public int getNumberInput() {
        scanner.reset();
        return scanner.nextInt();
    }

    public String getInput() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void clear(){
        System.out.flush();
    }

    public void sleep(int sleep) {
        try{
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    //TODO: Doesn't work yet
    public void checkUser() {
        sleep(1000);
//        try {
////            if (reader.readLine().equals("")) {
////                //if(input != null && !input.equals("") ) {
////                //System.out.println("User input: " + scanner.next());
////                System.out.println("Keep going");
////                sleep(1000);
////                System.out.println("Keep going");
////            }
//    } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    }

    public void confirmation() {
        System.out.println("Press enter to continue");
        try {
            reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearScreen() {
        try
        {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows"))
            {
                Runtime.getRuntime().exec("cls");
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e)
        {
            //  Handle any exceptions.
        }
    }


    public Hero wait(Hero hero, GameView view) {
        System.out.println("Waiting on user");
        hero.wait = true;
        while (hero.wait)
            try {
                Thread.sleep(500);
                //hero.addXp(100);
                //hero.takeDamage(1);
                hero.addGold(5);
                view.update(hero);
            } catch (Exception e) {

            }
        return hero;
    }

}
