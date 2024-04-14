package Character.Mystics;
import Character.Hero;
import Character.Stats.Stats;
import Dungeon.Enemy;

public class HuntingKnife extends MysticInterface{
    @Override
    public String rarityString() {
        return "Starter";
    }

    @Override
    public String nameString() {
        return "Hunting Knife";
    }

    @Override
    public String hoverTextString() {
        return "Hunting Knife - Killed enemies drop 25% more gold, heal 10 health when you open a chest";
    }

    @Override
    public String IconName() {
        return "HuntingKnife";
    }

    @Override
    public Stats passiveBuffs(Stats stats) {
        return stats;
    }

    @Override
    public Hero onKill(Hero hero, Enemy enemy) {
        int goldBonus =  (int) Math.floor((enemy.getGold() / 4));
        hero.addGold(goldBonus);
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
        hero.heal(10);
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
        return 0;
    }
}
