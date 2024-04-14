package Character.Abilities;
import Character.Hero;
import Character.Combat.AttackObject;
import Character.Stats.Stats;
import Dungeon.Enemy;

public class ArcaneBeam extends AbilityInterface{
    Stats stats = new Stats(0, 0, 0, 40, 0, 0);
    @Override
    public String IconName() {
        return "ArcaneBeam";
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
        return "Arcane Beam";
    }

    @Override
    public String getDescription() {
        return "Expends all mana to deal massive damage.";
    }

    @Override
    public AttackObject useAbility(Hero hero, Enemy enemy) {
        // Should it do anything else?
        AttackObject attack = new AttackObject();
        attack.setMagicalAttack();
        attack.setDamage((int) (hero.getMana() * 1.25));
        hero.setAnnouncement("You cast Arcane Beam for " + attack.getDamage() + " magic damage!");
        return attack;
    }

    @Override
    public Hero payAbility(Hero hero) {
        hero.setMana(0);
        return hero;
    }

    @Override
    public boolean canCast(Hero hero) {
        if(hero.getMana()>0)
            return true;
        return false;
    }
}
