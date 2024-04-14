package Character.Equipment;

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

    @Override
    public String hoverString() {
        return name  + " Type: " + type + " Amplifier: " + amplifier + ": Value: " + value;
    }


    @Override
    public String toString(){
        return hoverString();
        //return "Artifact: " + name + " Value: " + value + " Description: " + description + " Type: " + type + " Amplifier: " + amplifier;
    }
}
