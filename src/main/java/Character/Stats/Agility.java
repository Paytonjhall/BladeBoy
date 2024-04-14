package Character.Stats;

public class Agility extends Stat {
    private int value = 0;
    private String name = "Agility";
    private String description = "Agility is a measure of speed and reflexes. It affects how often you can dodge attacks.";

    public Agility(int value) {
        this.value = value;
    }

    @Override
    public int value() {
        return this.value;
    }

    @Override
    public int setValue(int value) {
        this.value = value;
        return this.value;
    }

    @Override
    public int addValue(int add) {
        this.value+=add;
        return this.value;
    }

    @Override
    public int subtractValue(int sub) {
        this.value-=sub;
        return this.value;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public String description() {
        return this.description;
    }

    @Override
    public String icon() {
        return "Agility";
    }

    @Override
    public double effect() {
        // Dodge chance is increased by 1 percent per point of agility to a max of 75%
        // returning chance to hit.
        double dodgeChance = this.value /100;
        if(dodgeChance > 0.75){
            return 1 - 0.75;
        }
        return 1 - (this.value / 100);

    }
}
