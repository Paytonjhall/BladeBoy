package Dungeon;
import Character.*;

public class Adventure {
    Hero hero;
    int level = 0;
    int fights = 0;
    public Adventure(Hero hero) {
        this.hero = hero;
        level = hero.getDungeonCount();
        fights = 4 + (level * 2);

    }
}
