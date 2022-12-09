package Test;
import Character.*;
import Character.Town.Oracle;

import java.util.List;

public class TestMystic {
    public static void main(String[] args) throws InterruptedException {
        //testMystic();
        testHeroMystics(5);
    }

    public static void testMystic(){

        Mystic mystic1 = new Mystic(1);
        Mystic mystic2 = new Mystic(2);
        Mystic mystic3 = new Mystic(3);

        mystic1.createMystic();
        mystic2.createMystic();
        mystic3.createMystic();

        System.out.println(mystic1.toString());
        System.out.println(mystic2.toString());
        System.out.println(mystic3.toString());
    }

    public static void testHeroMystics(int num) throws InterruptedException {
        Hero hero = new Hero(num);
        Oracle oracle = new Oracle();
        oracle.visitOracle(hero);
    }
}
