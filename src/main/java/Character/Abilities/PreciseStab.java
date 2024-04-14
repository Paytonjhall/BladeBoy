package Character.Abilities;
import Character.Hero;
import Character.Combat.AttackObject;
import Character.Stats.Stats;
import Dungeon.Enemy;

public class PreciseStab extends AbilityInterface {
    Stats stats = new Stats(0, 0, 12, 0, 0, 12);
    @Override
    public String IconName() {
        return "PreciseStab";
    }

    @Override
    public boolean statRequirements(Stats stats) {
        return compareStats(stats, this.stats);
    }

    @Override
    public Stats getRequirements() {
        return stats;
    }

    @Override
    public String getName() {
        return "Precise Stab";
    }

    @Override
    public String getDescription() {
        return "A precise stab that deals 80% of your base damage with a very high critical hit chance";
    }

    @Override
    public AttackObject useAbility(Hero hero, Enemy enemy) {
        AttackObject attack = new AttackObject();
        attack.setDamage((int) (hero.getBaseDamage() * .8));
        attack.setCrit(80);
        return attack;
    }

    @Override
    public Hero payAbility(Hero hero) {
        return null;
    }

    @Override
    public boolean canCast(Hero hero) {
        return false;
    }
}
