package Character.Mystics;
import Character.Hero;
import Character.Stats.Stats;
import Dungeon.Enemy;

public class VenomOrb extends MysticInterface{
    int venomCount = 0;
    String enemyName = "";
    @Override
    public String rarityString() {
        return "common";
    }

    @Override
    public String nameString() {
        return "Venom Orb";
    }

    @Override
    public String hoverTextString() {
        return "Venom Orb - each attack does 5% more than the last";
    }

    @Override
    public String IconName() {
        return "VenomOrb";
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
        return 0;
    }

    @Override
    public int onAttack(Hero hero, int damage, Enemy enemy) {
        if (enemy.getName() != enemyName) {
            venomCount = 0;
            enemyName = enemy.getName();
        }
        venomCount ++;
        double damageIncrease = venomCount * .05;
        int bonusDamage = (int) (damage * damageIncrease);
        return bonusDamage;
    }
}
