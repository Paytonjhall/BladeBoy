package Character.Stats;

public class Strength extends Stat {
    private int value = 0;
    private String name = "Strength";
    private String description = "Strength is a measure of physical power. It affects how much damage you can deal with physical attacks.";

    public Strength(int value) {
        this.value = value;
    }

    @Override
    public int value() {
        return value;
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
        return "Strength";
    }

    @Override
    public double effect() {
        // Damage is increased by 4 percent per point of strength
        return 1 + (this.value / 25);
    }
}
