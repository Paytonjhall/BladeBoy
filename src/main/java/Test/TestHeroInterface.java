package Test;
import Character.*;
import Character.Town.ArmorGenerator;
import Character.Town.ArtifactGenerator;
import Character.Town.WeaponGenerator;
import view.HeroInventory;

import java.util.ArrayList;
import java.util.List;

public class TestHeroInterface {
    public static void main(String[] args) {
        WeaponGenerator wg = new WeaponGenerator();
        ArmorGenerator ag = new ArmorGenerator();
        ArtifactGenerator artg = new ArtifactGenerator();
        List<Mystic> mystics = new ArrayList<Mystic>();
        Hero hero = new Hero(new Armor("Plated Leather Armor", 1000, "Plain", .35),
                new Weapon("Incredible Steel Sword", 1000, "An impressively sharp steel blade", 15),
                new Artifact("Fortune Amulet", 1000, "A old amulet from your mother", "Fortune", 1.1625),
                180, 500, 0, 8, 500, mystics);

//        hero.addToBag(new Weapon("Incredible Iron Sword", 1000, "An impressively sharp steel blade", 15));
//        hero.addToBag(new Weapon("Incredible Gold Sword", 1000, "An impressively sharp steel blade", 15));
//        hero.addToBag(new Weapon("Incredible Diamond Sword", 1000, "An impressively sharp steel blade", 15));
//        hero.addToBag(new Weapon("Incredible Copper Sword", 1000, "An impressively sharp steel blade", 15));

        // assert hero.getBag().size() > 0;
        // hero.getWeapon().setIconPath("Swords/sword58.png");
        hero.addToBag(artg.generateArtifact(1, 1).get(0));
        hero.addToBag(artg.generateArtifact(1, 1).get(0));
        hero.addToBag(artg.generateArtifact(1, 1).get(0));
        hero.addToBag(ag.generateArmor(1,1).get(0));
        hero.addToBag(wg.generateWeapons(1, 10).get(0));
        hero.addToBag(wg.generateWeapons(1, 10).get(0));
        hero.addToBag(wg.generateWeapons(1, 10).get(0));
        hero.setWeapon(wg.generateWeapons(10,1).get(0));
        hero.setArmor(ag.generateArmor(10,1).get(0));
        hero.setArtifact(artg.generateArtifact(10,1).get(0));
        HeroInventory heroInventory = new HeroInventory();
        heroInventory.openInventory(hero);

    }
}
