package Character.Abilities;
import Character.Hero;
import Character.Combat.AttackObject;
import Character.Stats.Stats;
import Dungeon.Enemy;

public class InnerFire extends AbilityInterface{
    Stats stats = new Stats(12, 8, 0, 4, 10, 0);
    int manaCost = 30;
    @Override
    public String IconName() {
        return "InnerFire";
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
        return "Inner Fire";
    }

    @Override
    public String getDescription() {
        return "Heals for 50% of attack damage and provides that much block.";
    }

    @Override
    public AttackObject useAbility(Hero hero, Enemy enemy) {
        AttackObject attack = new AttackObject();
        attack.setHeal((int) (hero.getBaseDamage() * .5));
        attack.setBlock((int) (hero.getBaseDamage() * .5));
        hero.setAnnouncement("You cast Inner Fire, healing " + attack.getHeal() + " health and providing " + attack.getBlock() + " block!");
        return attack;
    }

    @Override
    public Hero payAbility(Hero hero) {
        hero.useMana(manaCost);
        return hero;
    }

    @Override
    public boolean canCast(Hero hero) {
        return hero.getMana() >= manaCost;
    }
}
