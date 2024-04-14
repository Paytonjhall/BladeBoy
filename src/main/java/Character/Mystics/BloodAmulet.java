package Character.Mystics;
import Character.Hero;
import Character.Stats.Stats;
import Dungeon.Enemy;

public class BloodAmulet extends MysticInterface {

    @Override
    public String rarityString() {
        return "Starter";
    }

    @Override
    public String nameString() {
        return "Blood Amulet";
    }

    @Override
    public String hoverTextString() {
        return "Blood Amulet - On kill, gain 10% of enemies health back, you take 10% more damage";
    }

    @Override
    public String IconName() {
        return "BloodAmulet";
    }

    @Override
    public Stats passiveBuffs(Stats stats) {
        return stats;
    }

    @Override
    public Hero onKill(Hero hero, Enemy enemy) {
        hero.heal((int) (enemy.getMaxHealth() * 0.1));
        System.out.println("You gained health back!");
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
        // We simply just add the damage, thats why its 0.1 and not 1.1
        System.out.println("You took an additional: " + (damage * 0.1) + " damage");
        return (int) (damage * 0.1);
    }

    @Override
    public int onAttack(Hero hero, int damage, Enemy enemy) {
        return 0;
    }
}
