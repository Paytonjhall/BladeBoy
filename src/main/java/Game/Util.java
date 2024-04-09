package Game;

import java.util.Random;

public class Util {
    Random rnd = new Random();
    public int getRandomNum(int range){
        return (int) Math.floor(rnd.nextInt(range) + 1);
    }
}
