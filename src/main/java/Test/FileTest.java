package Test;
import Character.*;
import Game.HeroSaveAdapter;
import Game.Load;
import Game.Save;

import java.util.ArrayList;
import java.util.List;

public class FileTest {


    public static void main(String[] args) {
        List<Mystic> mystics = new ArrayList<Mystic>();
        Hero hero = new Hero(new Armor("Plated Leather Armor", 1000, "Plain", .35),
                new Weapon("Incredible Steel Sword", 1000, "An impressively sharp steel blade", 15),
                new Artifact("Fortune Amulet", 1000, "A old amulet from your mother", "Fortune", 1.1625),
                180, 500, 0, 8, 500, mystics);
        test(hero);
    }

    public static void test(Hero hero){
        hero.setUsername("Payton");
        Save save = new Save();
        save.saveGame(hero);
        Load load = new Load();
        load.loadGame(hero.getUsername());
    }
}
