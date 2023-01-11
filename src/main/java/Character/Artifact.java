package Character;

public class Artifact implements ItemInterface {

    //Artifacts apply special abilities to the hero
    //Types:
    //Fortune --> more gold found
    //Discount --> less gold spent
    //Luck --> more chance of finding rare items
    //Strength --> more damage
    //Defense --> more armor
    //Health --> more health
    //Vampire --> more health when attacking
    //Conqueror --> health after fight healed
    //Learner --> more xp gained

    String name;
    int value;
    String description;
    String type;
    String iconPath;
    double amplifier;
    double fortune = 1;
    double discount = 1;
    double luck = 0;
    double strength = 0;
    double defense = 0;
    double health = 0;
    double vampire = 0;
    double conqueror = 0;
    double learner = 1;
    public Artifact(String name, int value, String description, String type, double amplifier) {
        this.name = name;
        this.value = value;
        this.description = description;
        this.type = type;
        this.amplifier = amplifier;

        switch (type) {
            case "Fortune" -> fortune=amplifier; //Implemented
            case "Discount" -> discount=amplifier;
            case "Luck" -> luck=amplifier;
            case "Strength" -> strength=amplifier;
            case "Defense" -> defense=amplifier;
            case "Health" -> health=amplifier;
            case "Vampire" -> vampire=amplifier;
            case "Conqueror" -> conqueror=amplifier;
            case "Learner" -> learner=amplifier;
        }
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public int artifactAmplify(int value){
        return (int)(value*amplifier);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmplifier() {
        return amplifier;
    }

    public void setAmplifier(double amplifier) {
        this.amplifier = amplifier;
    }

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
        return name;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public double getFortune() {
        return fortune;
    }

    public void setFortune(double fortune) {
        this.fortune=fortune;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount=discount;
    }

    public double getLuck() {
        return luck;
    }

    public void setLuck(double luck) {
        this.luck=luck;
    }

    public double getStrength() {
        return strength;
    }

    public void setStrength(double strength) {
        this.strength=strength;
    }

    public double getDefense() {
        return defense;
    }

    public void setDefense(double defense) {
        this.defense=defense;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health=health;
    }

    public double getVampire() {
        return vampire;
    }

    public void setVampire(double vampire) {
        this.vampire=vampire;
    }

    public double getConqueror() {
        return conqueror;
    }

    public void setConqueror(double conqueror) {
        this.conqueror=conqueror;
    }

    public double getLearner() {
        return learner;
    }

    public void setLearner(double learner) {
        this.learner=learner;
    }

    @Override
    public String toString(){
        return "Artifact: " + name + " Value: " + value + " Description: " + description + " Type: " + type + " Amplifier: " + amplifier;
    }
}
