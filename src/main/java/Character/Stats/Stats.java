package Character.Stats;

public class Stats {

    public enum stat {
        constitution,
        strength,
        agility,
        intelligence,
        defense,
        luck
    }
    Constitution constitution;
    Strength strength;
    Agility agility;
    Intelligence intelligence;
    Defense defense;
    Luck luck;

    public Stats(int constitution, int strength, int agility, int intelligence, int defense, int luck) {
        this.constitution = new Constitution(constitution);
        this.strength = new Strength(strength);
        this.agility = new Agility(agility);
        this.intelligence = new Intelligence(intelligence);
        this.defense = new Defense(defense);
        this.luck = new Luck(luck);
    }

    public int setStat(stat sat, int value) {
        switch (sat) {
            case constitution:
                this.constitution.setValue(value);
                break;
            case strength:
                this.strength.setValue(value);
                break;
            case agility:
                this.agility.setValue(value);
                break;
            case intelligence:
                this.intelligence.setValue(value);
                break;
            case defense:
                this.defense.setValue(value);
                break;
            case luck:
                this.luck.setValue(value);
                break;
        }
        System.out.println("Error when setting stat, stat not found");
        return 0;
    }

    public int getStat(stat sat) {
        switch (sat) {
            case constitution:
                return this.constitution.value();
            case strength:
                return this.strength.value();
            case agility:
                return this.agility.value();
            case intelligence:
                return this.intelligence.value();
            case defense:
                return this.defense.value();
            case luck:
                return this.luck.value();
        }
        System.out.println("Error when getting stat, stat not found");
        return 0;
    }

    public int incrementStat(stat stat, int value) {
        switch (stat) {
            case constitution:
                return this.constitution.addValue(value);
            case strength:
                return this.strength.addValue(value);
            case agility:
                return this.agility.addValue(value);
            case intelligence:
                return this.intelligence.addValue(value);
            case defense:
                return this.defense.addValue(value);
            case luck:
                return this.luck.addValue(value);
        }
        System.out.println("Error when incrementing stat, stat not found");
        return 0;
    }

    public int decrementStat(stat stat, int value) {
        switch (stat) {
            case constitution:
                return this.constitution.subtractValue(value);
            case strength:
                return this.strength.subtractValue(value);
            case agility:
                return this.agility.subtractValue(value);
            case intelligence:
                return this.intelligence.subtractValue(value);
            case defense:
                return this.defense.subtractValue(value);
            case luck:
                return this.luck.subtractValue(value);
        }
        System.out.println("Error when decrementing stat, stat not found");
        return 0;
    }

    public Constitution getConstitution() {
        return constitution;
    }

    public void setConstitution(Constitution constitution) {
        this.constitution = constitution;
    }

    public Strength getStrength() {
        return strength;
    }

    public void setStrength(Strength strength) {
        this.strength = strength;
    }

    public Agility getAgility() {
        return agility;
    }

    public void setAgility(Agility agility) {
        this.agility = agility;
    }

    public Intelligence getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(Intelligence intelligence) {
        this.intelligence = intelligence;
    }

    public Defense getDefense() {
        return defense;
    }

    public void setDefense(Defense defense) {
        this.defense = defense;
    }

    public Luck getLuck() {
        return luck;
    }

    public void setLuck(Luck luck) {
        this.luck = luck;
    }
}
