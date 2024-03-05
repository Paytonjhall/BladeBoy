package Test;
import Character.Equipment.Weapon;
import Character.Town.*;
import Character.*;


import java.util.List;

public class TestWeapons {
    public static void main(String[] args) {
        // testWeapon();
        // Need to get a hero object in here to get the level.
    }

    public static void testWeapon(Hero hero){
        WeaponGenerator wg = new WeaponGenerator(hero);
        List<Weapon> weapons = wg.generateWeapons(1, 10);

        for (Weapon w : weapons){
            System.out.println(w.toString());
        }

        weapons = wg.generateWeapons(3,10);
        System.out.println("\n\n");
        for (Weapon w : weapons){
            System.out.println(w.toString());
        }
    }
}

