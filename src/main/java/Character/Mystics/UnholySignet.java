package Character.Mystics;
import Character.Hero;
import Character.Stats.Stats;
import Dungeon.Enemy;

public class UnholySignet extends MysticInterface {
    int count = 0;
    @Override
    public String rarityString() {
        return "common";
    }

    @Override
    public String nameString() {
        return "Unholy Signet";
    }

    @Override
    public String hoverTextString() {
        return "Unholy Signet - Every 5th attack does double damage";
    }

    @Override
    public String IconName() {
        return "UnholySignet";
    }

    @Override
    public Stats passiveBuffs(Stats stats) {
        return stats;
    }

    @Override
    public int onAttack(Hero hero, int damage, Enemy enemy) {
        count ++;
        if (count >= 5) {
            count = 0;
            return damage;
        }
        return 0;
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

    @Override
    public Hero onProgressFloor(Hero hero) {
        return hero;
    }

    @Override
    public Hero onPickUp(Hero hero) {
        return hero;
    }
}
