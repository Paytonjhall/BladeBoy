package Character.Stats;

public class Luck extends Stat{
    private int value = 0;
    private String name = "Luck";
    private String description = "Luck is a measure of how lucky you are. It affects items you find, prices, etc...";

    public Luck(int value) {
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
        return "Luck";
    }

    @Override
    public double effect() {
        // Luck is a percentage increase in chance of finding items
        // Every 20 adds a tier of luck
        return 1 + (this.value / 20);
    }
}
