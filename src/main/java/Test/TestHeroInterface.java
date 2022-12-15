package Test;
import Character.*;

import java.util.ArrayList;
import java.util.List;

public class TestHeroInterface {
    public static void main(String[] args) {
        List<Mystic> mystics = new ArrayList<Mystic>();
        Hero hero = new Hero(new Armor("Plated Leather Armor", 1000, "Plain", .35),
                new Weapon("Incredible Steel Sword", 1000, "An impressively sharp steel blade", 15),
                new Artifact("Fortune Amulet", 1000, "A old amulet from your mother", "Fortune", 1.1625),
                180, 500, 0, 8, 500, mystics);

        hero.addToBag(new Weapon("Incredible Iron Sword", 1000, "An impressively sharp steel blade", 15));
        hero.addToBag(new Weapon("Incredible Gold Sword", 1000, "An impressively sharp steel blade", 15));
        hero.addToBag(new Weapon("Incredible Diamond Sword", 1000, "An impressively sharp steel blade", 15));
        hero.addToBag(new Weapon("Incredible Copper Sword", 1000, "An impressively sharp steel blade", 15));

        hero.organizeBag();

        System.out.println(hero.getWeapon().toString());
    }
}
