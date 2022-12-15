package Character;

public class Potion {
    String type;
    int effect;
    int value;

    //Types: Heal Potion, God's Strength, Defense, Survival, Double Tap, Scavenge potion (doubles rewards and garuentees item drop)

    public Potion(String type, int effect, int value) {
        this.type = type;
        this.effect = effect;
        this.value = value;
    }

    public Potion(){

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getEffect() {
        return effect;
    }

    public void setEffect(int effect) {
        this.effect = effect;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String toString(){
        return "Potion: " + type + " - " + effect + ", gold : " + value;
    }
}
