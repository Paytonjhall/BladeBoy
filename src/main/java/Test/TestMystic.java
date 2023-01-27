package Test;
import Character.*;
import Character.Abilities.Mystic;
import Character.Town.Oracle;

public class TestMystic {
    public static void main(String[] args) throws InterruptedException {
        //testMystic();
        testHeroMystics(5);
    }

    public static void testMystic(){

        Mystic mystic1 = new Mystic(1);
        Mystic mystic2 = new Mystic(1);
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
        hero.setMaxHealth(100);
        hero.setHealth(100);
        Oracle oracle = new Oracle();
        oracle.visitOracle(hero);
        System.out.println(hero.getMaxHealth());
    }
}
