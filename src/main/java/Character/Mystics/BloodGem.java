package Character.Mystics;
import Character.Hero;
import Dungeon.Enemy;

public class BloodGem extends MysticInterface{
    @Override
    public String rarityString() {
        return "common";
    }

    @Override
    public String nameString() {
        return "Blood Gem";
    }

    @Override
    public String hoverTextString() {
        return "Blood Gem - Gain 20% life steal, deal 20% less damage";
    }

    @Override
    public String IconName() {
        return "BloodGem";
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
    public Hero onChest(Hero hero) {
        return hero;
    }

    @Override
    public Hero onLevelUp(Hero hero) {
        return hero;
    }

    @Override
    public int onHit(Hero hero, int damage) {
        return 0;
    }

    @Override
    public int onAttack(Hero hero, int damage, Enemy enemy) {
        int dmgChange = (int) Math.ceil(damage/5);
        hero.heal(dmgChange);
        return -1 * dmgChange;
    }
}
