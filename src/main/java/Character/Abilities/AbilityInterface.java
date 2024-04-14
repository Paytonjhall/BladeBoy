package Character.Abilities;
import Character.*;
import Character.Combat.AttackObject;
import Character.Stats.Stats;
import Dungeon.Enemy;

public abstract class AbilityInterface {
    Stats requirements;
    String name;
    String description;
    String iconName;
    public abstract String IconName();
    public abstract boolean canCast(Hero hero);
    public abstract Hero payAbility(Hero hero);
    public abstract AttackObject useAbility(Hero hero, Enemy enemy);
    public abstract boolean statRequirements(Stats stats);
    public abstract Stats getRequirements();

    public void setRequirements(Stats requirements) {
        this.requirements = requirements;
    }

    public abstract String getName();

    public abstract String getDescription();

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public boolean compareStats(Stats heroStats, Stats abilityRequirements) {
        return heroStats.getConstitution().value() >= abilityRequirements.getConstitution().value() &&
                heroStats.getStrength().value() >= abilityRequirements.getStrength().value() &&
                heroStats.getAgility().value() >= abilityRequirements.getAgility().value() &&
                heroStats.getIntelligence().value() >= abilityRequirements.getIntelligence().value() &&
                heroStats.getIntelligence().value() >= abilityRequirements.getIntelligence().value() &&
                heroStats.getLuck().value() >= abilityRequirements.getLuck().value();
    }
}
