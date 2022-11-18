package Game;

import java.io.StringReader;
import java.util.Scanner;

public class UserInput {
    Scanner scanner = new Scanner(System.in);

    public int getNumberInput() {
        return scanner.nextInt();
    }

    public void clear(){
        System.console().flush();
    }

    public void sleep(int sleep) {
        try{
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
