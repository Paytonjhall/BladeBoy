package Character.Mystics;
import Character.Hero;
import Character.Abilities.Mystic;
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
    public int onAttack(Hero hero, int damage, Enemy enemy) {
        count ++;
        if (count == 5) {
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
}
