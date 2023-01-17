package Test;
import Character.*;
import Character.Town.ArmorGenerator;
import Character.Town.ArtifactGenerator;
import Character.Town.WeaponGenerator;
import Game.AssetPath;
import Game.UserInput;
import view.GameView;
import view.HeroInventory;
import view.blacksmith;
import view.armory;
import java.util.ArrayList;
import java.util.List;

public class TestHeroInterface {
    public static void main(String[] args) throws Exception {
        WeaponGenerator wg = new WeaponGenerator();
        ArmorGenerator ag = new ArmorGenerator();
        blacksmith bs = new blacksmith();
        armory ar = new armory();
        ArtifactGenerator artg = new ArtifactGenerator();
        AssetPath assetPath = new AssetPath();
        UserInput ui = new UserInput();
        GameView gameView = new GameView();
        List<Mystic> mystics = new ArrayList<Mystic>();
        Hero hero = new Hero(new Armor("Plated Leather Armor", 1000, "Plain", .35),
                new Weapon("Incredible Steel Sword", 1000, "An impressively sharp steel blade", 15),
                new Artifact("Fortune Amulet", 1000, "A old amulet from your mother", "Fortune", 1.1625),
                180, 500, 450, 8, 500, mystics);

//        hero.addToBag(new Weapon("Incredible Iron Sword", 1000, "An impressively sharp steel blade", 15));
//        hero.addToBag(new Weapon("Incredible Gold Sword", 1000, "An impressively sharp steel blade", 15));
//        hero.addToBag(new Weapon("Incredible Diamond Sword", 1000, "An impressively sharp steel blade", 15));
//        hero.addToBag(new Weapon("Incredible Copper Sword", 1000, "An impressively sharp steel blade", 15));




            Potion potion = new Potion("Heal Potion", 40, 100);
            potion.setIconPath(assetPath.healthPotion);
            Potion potion2 = new Potion("Power Potion", 40, 100);
            potion2.setIconPath(assetPath.healthPotion);
            hero.addPotion(potion);
            hero.addPotion(potion2);

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
        //hero.setWeapon(null);
        hero.setHealth(hero.getMaxHealth()/2);
        //bs.visitBlackSmith(hero);
        // hero = gameView.startGameView(hero);
        //hero = heroInventory.openInventory(hero);
        ar.visitArmory(hero);

        ui.wait(hero, gameView);


        System.out.println(hero.getWeapon().toString());

    }
}
