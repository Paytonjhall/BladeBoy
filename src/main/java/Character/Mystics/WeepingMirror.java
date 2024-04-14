package Character.Mystics;
import Character.Hero;
import Character.Stats.Stats;
import Dungeon.Enemy;

public class WeepingMirror extends MysticInterface {
    int count = 0;
    @Override
    public String rarityString() {
        return "rare";
    }

    @Override
    public String nameString() {
        return "Weeping Mirror";
    }

    @Override
    public String hoverTextString() {
        return "Weeping Mirror - You dodge every 4th attack";
    }

    @Override
    public String IconName() {
        return "WeepingMirror";
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
    public Hero onProgressFloor(Hero hero) {
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
        count ++;
        if(count >= 4 && damage > 0) {
            System.out.println("Damage Blocked by Weeping Mirror: " + damage);
            count = 0;
            return -1 * damage;
        }
        return 0;
    }

    @Override
    public int onAttack(Hero hero, int damage, Enemy enemy) {
        return 0;
    }
}
