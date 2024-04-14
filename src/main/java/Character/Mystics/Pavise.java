package Character.Mystics;
import Character.Hero;
import Character.Stats.Stats;
import Dungeon.Enemy;
import Game.Util;

public class Pavise extends MysticInterface {
    @Override
    public String rarityString() {
        return "Starter";
    }

    @Override
    public String nameString() {
        return "Pavise";
    }

    @Override
    public String hoverTextString() {
        return "Pavise - 20% Chance to take half damage from attack";
    }

    @Override
    public String IconName() {
        return "Pavise";
    }

    @Override
    public Stats passiveBuffs(Stats stats) {
        return stats;
    }

    @Override
    public Hero onKill(Hero hero, Enemy enemy) {
        return hero;
    }

    @Override
    public Hero onProgressFloor (Hero hero) {
        return hero;
    }

    @Override
    public Hero onPickUp(Hero hero) {
        return hero;
    }

    @Override
    public Hero onChest(Hero hero) {
        return hero;
    }

    @Override
    public Hero onLevelUp(Hero hero) {
        return hero;
    }

    @Override
    public int onHit(Hero hero, int damage) {
        Util rnd = new Util();
        if(rnd.getRandomNum(100) > 20) {
            // We return negative damage, we simply add damage from mystics, so a negative half takes away half the damage. Cannot go below 0
            return (int)  -1 * (damage / 2);
        }
        return 0;
    }

    @Override
    public int onAttack(Hero hero, int damage, Enemy enemy) {
        return 0;
    }
}
