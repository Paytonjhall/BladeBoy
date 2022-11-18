package Character;

import Game.UserInput;

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
                case 1:
                    System.out.println("Starting new game...");
                    Thread.sleep(2000);
                    NewGame newGame = new NewGame();
                    newGame.start();
                    check = false;
                    break;
                case 2:
                    System.out.println("Loading game...");
                    check = false;
                    break;
                case 3:
                    System.out.println("Exiting game...");
                    System.exit(0);
                default:
                    System.out.println("Invalid input. Try again.");
                    choice = input.getNumberInput();
                    break;
            }

        }
    }
}

