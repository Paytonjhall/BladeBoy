package Character.Equipment;

public class newArtifacts implements ItemInterface {

    // Artifacts now have the following:
    // A rarity: the rarity means how many buffs they give,
    // The buffs they provide: gainManaOnKill, manaGainedOnHit, manaDrainOnAttack, abilityBonusDamage
    public Rarity rarity;
    public String name = "";
    public int value = 0;
    public String description = "";
    public int gainManaOnKill = 0;
    public int manaGainedOnHit = 0;
    public int manaDrainOnAttack = 0;
    public int abilityBonusDamage = 0;








    public Rarity getRarity(){return rarity;};
    public void setRarity(Rarity rarity) {this.rarity = rarity;};

    public int getGainManaOnKill(){return gainManaOnKill;};
    public void setGainManaOnKill(int gainManaOnKill) {this.gainManaOnKill = gainManaOnKill;};

    public int getManaGainedOnHit(){return manaGainedOnHit;};
    public void setManaGainedOnHit(int manaGainedOnHit) {this.manaGainedOnHit = manaGainedOnHit;};

    public int getManaDrainOnAttack(){return manaDrainOnAttack;};
    public void setManaDrainOnAttack(int manaDrainOnAttack) {this.manaDrainOnAttack = manaDrainOnAttack;};

    public int getAbilityBonusDamage(){return abilityBonusDamage;};
    public void setAbilityBonusDamage(int abilityBonusDamage) {this.abilityBonusDamage = abilityBonusDamage;};

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public void setDescription(String desc) {
        this.description = desc;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getValue() {
        return this.value;
    }

    @Override
    public String getDescription() {
        return this.getDescription();
    }

    @Override
    public String hoverString() {
        // TODO: Not sure what this should be.
        return this.getDescription();
    }


}
