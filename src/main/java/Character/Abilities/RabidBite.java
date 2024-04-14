package Character.Abilities;

import Character.Combat.AttackObject;
import Character.Stats.Stats;
import Dungeon.Enemy;
import Character.Hero;

public class RabidBite extends AbilityInterface{
    Stats stats = new Stats(10, 14, 2, 0,0,0);
    String IconName = "RabidBite";
    String name = "Rabid Bite";
    String description = "A powerful bite that deals .75x damage and heals for 25% of the damage dealt";
    int manaCost = 25;

    @Override
    public String IconName() {
        return IconName;
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
        return name;
    }

    @Override
    public String getDescription() {
        return "A powerful bite that deals .75x damage and heals for 25% of the damage dealt";
    }

    @Override
    public AttackObject useAbility(Hero hero, Enemy enemy) {
        // Physical Life steal attack
        AttackObject attack = new AttackObject();
        attack.setDamage((int) (hero.getBaseDamage() * .75));
        attack.setLifeSteal(.25);
        hero.setAnnouncement("You bit the enemy for " + attack.getDamage() + " damage and healed for " + attack.getLifeSteal() * attack.getDamage() + " health");
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
