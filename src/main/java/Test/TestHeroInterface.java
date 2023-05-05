package Test;
import Character.*;
import Character.Abilities.Mystic;
import Character.Equipment.Armor;
import Character.Equipment.Artifact;
import Character.Equipment.Weapon;
import Character.Town.ArmorGenerator;
import Character.Town.ArtifactGenerator;
import Character.Town.PotionGenerator;
import Character.Town.WeaponGenerator;
import Game.AssetPath;
import Game.UserInput;
import view.*;

import java.util.ArrayList;
import java.util.List;

public class TestHeroInterface {
    public static void main(String[] args) throws Exception {
        WeaponGenerator wg = new WeaponGenerator();
        ArmorGenerator ag = new ArmorGenerator();
        PotionGenerator pg = new PotionGenerator();
        blacksmith bs = new blacksmith();
        armory ar = new armory();
        oracle or = new oracle();
        DungeonClearedData dcd = new DungeonClearedData();
        //ClearedDungeon cd = new ClearedDungeon(dcd);
        artificiary art = new artificiary();
        ArtifactGenerator artg = new ArtifactGenerator();
        AssetPath assetPath = new AssetPath();
        UserInput ui = new UserInput();
        GameView gameView = new GameView();
        List<Mystic> mystics = new ArrayList<Mystic>();
        Hero hero = new Hero(new Armor("Plated Leather Armor", 1000, "Plain", .35),
                new Weapon("Incredible Steel Sword", 1000, "An impressively sharp steel blade", 15),
                new Artifact("Fortune Amulet", 1000, "A old amulet from your mother", "Fortune", 1.1625),
                180, 500, 250, 8, 500, mystics);

//        hero.addToBag(new Weapon("Incredible Iron Sword", 1000, "An impressively sharp steel blade", 15));
//        hero.addToBag(new Weapon("Incredible Gold Sword", 1000, "An impressively sharp steel blade", 15));
//        hero.addToBag(new Weapon("Incredible Diamond Sword", 1000, "An impressively sharp steel blade", 15));
//        hero.addToBag(new Weapon("Incredible Copper Sword", 1000, "An impressively sharp steel blade", 15));


        // hero.addXp(30000);
//            Potion potion = new Potion("Heal Potion", 40, 100);
//            potion.setIconPath(assetPath.healthPotion);
//            Potion potion2 = new Potion("Power Potion", 40, 100);
//            potion2.setIconPath(assetPath.healthPotion);
            hero.addPotion(pg.makeXPotion(400, 300));
            hero.addPotion(pg.makeDevilPotion(400, 300));
            hero.addPotion(pg.makeHealthPotion(400, 300));

        // assert hero.getBag().size() > 0;
        // hero.getWeapon().setIconPath("Swords/sword58.png");
        hero.addToBag(artg.generateArtifact(hero.getLevel(), 1).get(0));
        hero.addToBag(artg.generateArtifact(hero.getLevel(), 1).get(0));
        hero.addToBag(artg.generateArtifact(hero.getLevel(), 1).get(0));
        hero.addToBag(ag.generateArmor(hero.getLevel(),1).get(0));
        hero.addToBag(wg.generateWeapons(hero.getLevel(), 10).get(0));
        hero.addToBag(wg.generateWeapons(hero.getLevel(), 10).get(0));
        hero.addToBag(wg.generateWeapons(hero.getLevel(), 10).get(0));
        hero.setWeapon(wg.generateWeapons(hero.getLevel(),1).get(0));
        hero.setArmor(ag.generateArmor(hero.getLevel(),1).get(0));
        hero.setArtifact(artg.generateArtifact(hero.getLevel(),1).get(0));
        HeroInventory heroInventory = new HeroInventory();


        //hero.setWeapon(null);
        //hero.setHealth(hero.getMaxHealth()/2);
        //bs.visitBlackSmith(hero);
        //hero = gameView.startGameView(hero);
        //hero = heroInventory.openInventory(hero);
        //hero = art.visitArtificiary(hero);
        //ar.visitArmory(hero);
        //or.visitOracle(hero);
        //art.visitArtificiary(hero);

        gameView.enterTown(hero);
        //hero = gameView.startNewDungeon(hero, 2);
        //gameView.loadDungeon();
        //gameView.loadBossFloor();
        //gameView.startCombat();
        ui.wait(hero, gameView);


        System.out.println(hero.getWeapon().toString());

    }
}
