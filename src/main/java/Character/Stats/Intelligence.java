package Character.Stats;

public class Intelligence extends Stat{
    private int value = 0;
    private String name = "Intelligence";
    private String description = "Intelligence is a measure of mental power. It affects how much damage you can deal with magical attacks and abilities.";

    public Intelligence(int value) {
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
        return "Intelligence";
    }

    @Override
    public double effect() {
        // Damage is increased by 2 percent per point of intelligence
        // returning % more damage you would deal.
        return 1 + (this.value / 50);
    }
}
