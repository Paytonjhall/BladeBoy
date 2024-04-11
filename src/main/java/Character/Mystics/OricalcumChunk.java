package Character.Mystics;
import Character.Hero;
import Dungeon.Enemy;

public class OricalcumChunk extends MysticInterface {
    @Override
    public String rarityString() {
        return "common";
    }

    @Override
    public String nameString() {
        return "Oricalcum Chunk";
    }

    @Override
    public String hoverTextString() {
        return "Oricalcum Chunk - Block 5 damage whenever you take damage";
    }

    @Override
    public String IconName() {
        return "OricalcumChunk";
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
        return -5;
    }

    @Override
    public int onAttack(Hero hero, int damage, Enemy enemy) {
        return 0;
    }
}
