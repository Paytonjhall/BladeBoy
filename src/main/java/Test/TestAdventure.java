package Test;

import Character.*;
import Character.Town.Oracle;
import Character.Town.Town;
import Dungeon.Adventure;

import java.util.ArrayList;
import java.util.List;

public class TestAdventure {

    public static void main(String[] args) {
        List<Mystic> mystics = new ArrayList<Mystic>();
        mystics.add(new Mystic("Strength", 1.5, "Passive", 1));
//        mystics.add(new Mystic("Health", 10, "Passive", 1));
//        mystics.add(new Mystic("Block", 2.1, "Passive", 1));
//        mystics.add(new Mystic("Vamperism", 15, "type?", 4));
        Hero hero = new Hero(new Armor("Plated Leather Armor", 1000, "Plain", .35),
                new Weapon("Incredible Steel Sword", 1000, "An impressively sharp steel blade", 15),
                new Artifact("Fortune Amulet", 1000, "A old amulet from your mother", "Fortune", 1.1625),
                180, 500, 0, 8, 500, mystics);
        hero.setDungeonCount(5);
        for(int i = 0; i < 6; i++){
            hero.addSkillPoint(i+1, false);
        }
        Oracle oracle = new Oracle();
        hero = oracle.visitOracle(hero);
        System.out.println(hero.getDungeonCount() + " : Dungeon Count");
        Adventure adventure = new Adventure();
        while(true) {
            hero = adventure.startAdventure(hero);
            System.out.println(hero.getDungeonCount() + " : Dungeon Count");
        }
    }
}
