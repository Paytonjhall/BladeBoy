package Character.Abilities;

import Character.Stats.Stats;

import java.util.ArrayList;
import java.util.List;

public class AbilityHelper {

    ArrayList<AbilityInterface> allAbilities;

    public AbilityHelper() {
        allAbilities = new ArrayList<AbilityInterface>(List.of(new RabidBite(), new ArcaneBeam()));
    }

    public boolean compareStats(Stats heroStats, Stats abilityRequirements) {
        if(heroStats == null) return false;
        return heroStats.getConstitution().value() >= abilityRequirements.getConstitution().value() &&
                heroStats.getStrength().value() >= abilityRequirements.getStrength().value() &&
                heroStats.getAgility().value() >= abilityRequirements.getAgility().value() &&
                heroStats.getIntelligence().value() >= abilityRequirements.getIntelligence().value() &&
                heroStats.getIntelligence().value() >= abilityRequirements.getIntelligence().value() &&
                heroStats.getLuck().value() >= abilityRequirements.getLuck().value();
    }

    public ArrayList<AbilityInterface> getAllUsableAbilities(Stats heroStats) {
        ArrayList<AbilityInterface> usableAbilities = new ArrayList<>();
        for (AbilityInterface ability : allAbilities) {
            if (compareStats(heroStats, ability.getRequirements())) {
                usableAbilities.add(ability);
            }
        }
        return usableAbilities;
    }
}
