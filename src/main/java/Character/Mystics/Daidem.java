package Character.Mystics;
import Character.Hero;
import Character.Stats.Constitution;
import Character.Stats.Stats;
import Dungeon.Enemy;

public class Daidem extends MysticInterface {

    public int count = 0;
    public boolean used = false;
    @Override
    public String rarityString() {
        return "rare";
    }

    @Override
    public String nameString() {
        return "Daidem";
    }

    @Override
    public String hoverTextString() {
        return "Daidem - Hero gains 10 extra health every 2 levels you have upon pick up.";
    }

    @Override
    public String IconName() {
        return "Daidem";
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
        if (!used) {
            Stats stats = hero.getStats();
            stats.getConstitution().addValue((int) Math.floor(count / 2));
            stats.setConstitution(new Constitution(stats.getConstitution().value() + (int) Math.floor(hero.getLevel()/ 2)));
            hero.setStats(stats);
            System.out.println("Passive bonuses AFTER Daidem: " + stats.getConstitution().value());
            used = true;
        }
        return hero;
    }

    @Override
    public Hero onChest(Hero hero) {
        return hero;
    }

    @Override
    public Hero onLevelUp(Hero hero) {
        count++;
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
