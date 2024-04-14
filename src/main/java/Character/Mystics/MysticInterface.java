package Character.Mystics;

import Character.Stats.Stats;
import Dungeon.Enemy;
import Character.Hero;

public abstract class MysticInterface {

    public abstract String rarityString ();
    public abstract String nameString ();
    public abstract String hoverTextString ();
    public abstract String IconName();
    public abstract Stats passiveBuffs(Stats stats);
    public abstract int onAttack (Hero hero, int damage, Enemy enemy);
    public abstract int onHit (Hero hero, int damage);
    public abstract Hero onLevelUp (Hero hero);
    public abstract Hero onChest (Hero hero);
    public abstract Hero onKill (Hero hero, Enemy enemy);
    public abstract Hero onProgressFloor(Hero hero);
    public abstract Hero onPickUp(Hero hero);


}
