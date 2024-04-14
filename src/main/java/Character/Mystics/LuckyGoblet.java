package Character.Mystics;

import Character.Stats.Luck;
import Character.Stats.Stats;
import Character.Hero;
import Dungeon.Enemy;

public class LuckyGoblet extends MysticInterface {
    int count = 3;
    @Override
    public String rarityString() {
        return "common";
    }

    @Override
    public String nameString() {
        return "Lucky Goblet";
    }

    @Override
    public String hoverTextString() {
        return "Lucky Goblet - Increase luck by 3 upon pickup";
    }

    @Override
    public String IconName() {
        return "LuckyGoblet";
    }

    @Override
    public Stats passiveBuffs(Stats stats) {
        return stats;
    }

    @Override
    public Hero onPickUp(Hero hero) {
        Stats stats = hero.getStats();
        stats.setLuck(new Luck(stats.getLuck().value() + 3));
        count = 0;
        hero.setStats(stats);
        return hero;
    }

    @Override
    public Hero onProgressFloor(Hero hero) {
        return hero;
    }

    @Override
    public Hero onKill(Hero hero, Enemy enemy) {
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
        return 0;
    }
}
