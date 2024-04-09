package Character.Mystics;
import Character.Hero;
import Dungeon.Enemy;
import Game.Util;

import java.util.Random;

public class MageTomb extends MysticInterface {


    @Override
    public String rarityString() {
        return "Starter";
    }

    @Override
    public String nameString() {
        return "Mages Tome";
    }

    @Override
    public String hoverTextString() {
        return "Mages Tome - 30% Chance to perice through 50% of enemies armor";
    }

    @Override
    public String IconName() {
        return "MagesTomb";
    }

    @Override
    public int onAttack(Hero hero, int damage, Enemy enemy) {
        Random rnd = new Random();
        Util ran = new Util();
        if(ran.getRandomNum(100) <= 30) {
            // damage =  (int) enemy.getArmorRating() / 2;
            damage = (int) damage / 2;
            System.out.println("Bonus damage from mage tome: " + damage);
        }
        return damage;
    }

    @Override
    public int onHit(Hero hero, int damage) {
        return 0;
    }

    @Override
    public Hero onLevelUp(Hero hero) {
        return hero;
    }

    @Override
    public Hero onChest(Hero hero) {
        return hero;
    }

    @Override
    public Hero onKill(Hero hero, Enemy enemy) {
        return hero;
    }
}
