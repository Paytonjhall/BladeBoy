package Game;

import Character.Town.Town;
import Game.UserInput;

import java.util.Scanner;
import Character.*;
public class Main {


    public static void main(String[] args) throws InterruptedException {
        // write your code here
        System.out.println("Welcome to BladeBoy!");
        System.out.println("[1]: Start New Game");
        System.out.println("[2]: Load Game");
        System.out.println("[3]: Exit Game");
        UserInput input = new UserInput();
        int choice = input.getNumberInput();
        boolean check = true;
        while (check) {
            switch (choice) {
                case 1 -> {
                    System.out.println("Starting new game...");
                    Thread.sleep(1000);
                    System.out.println("Please enter your name: ");
                    String name = input.getInput();
                    Thread.sleep(2000);
                    NewGame newGame = new NewGame();
                    newGame.start(name);
                    check = false;
                }
                case 2 -> {
                    check = false;
                    Load loadGame = new Load();
                    Hero hero = loadGame.loadGame("Pay");
                    Town town = new Town(hero);
                }
                case 3 -> {
                    System.out.println("Exiting game...");
                    System.exit(0);
                }
                default -> {
                    System.out.println("Invalid input. Try again.");
                    choice = input.getNumberInput();
                }
            }
        }
    }
}

