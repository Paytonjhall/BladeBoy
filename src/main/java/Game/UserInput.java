package Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Scanner;

public class UserInput {
    Scanner scanner = new Scanner(System.in);
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


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
}
