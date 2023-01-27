package Test;
import Character.Equipment.Weapon;
import Character.Town.*;

import java.util.List;

public class TestWeapons {
    public static void main(String[] args) {
        testWeapon();
    }

    public static void testWeapon(){
        WeaponGenerator wg = new WeaponGenerator();
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

