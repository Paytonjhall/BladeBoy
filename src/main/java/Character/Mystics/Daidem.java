package Character.Mystics;
import Character.Hero;
import Dungeon.Enemy;

public class Daidem extends MysticInterface {
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
        return "Daidem - Hero gains 5 extra health when leveling up";
    }

    @Override
    public String IconName() {
        return "Daidem";
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
        hero.setMaxHealth(hero.getMaxHealth() + 5);
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
