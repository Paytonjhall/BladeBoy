package Test;
import Character.*;
import Dungeon.EnemyGenerator;

public class TestEnemy {
    EnemyGenerator eg = new EnemyGenerator(1);
    public static void main(String[] args) {
        testEnemy();
    }

    public static void testEnemy(){
        EnemyGenerator eg = new EnemyGenerator(5);

        System.out.println(eg.generateEnemy());
        System.out.println(eg.generateEnemy());

    }
}

