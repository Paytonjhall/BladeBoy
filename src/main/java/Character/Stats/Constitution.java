package Character.Stats;

public class Constitution extends Stat{
    private int value = 0;
    private String name = "Constitution";
    private String description = "Constitution is a measure of physical health. It affects how much health you have.";

    public Constitution(int value) {
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
        return "Constitution";
    }

    @Override
    public double effect() {
        // Doesn't do anything yet, maybe prevents poison chance or something???
        return 10*this.value;
    }
}
