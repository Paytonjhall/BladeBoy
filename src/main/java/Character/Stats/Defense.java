package Character.Stats;

public class Defense extends Stat{
    private int value = 0;
    private String name = "Defense";
    private String description = "Defense is a measure of physical defense. It affects how much damage you take from physical attacks.";

    public Defense(int value) {
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
        return "Defense";
    }

    @Override
    public double effect() {
        // Damage taken is reduced by 2 percent per point of defense, to a max of 80%
        // Returning damage you would take.
        double damageReduction = this.value / 50;
        if(damageReduction > 0.8){
            return 1 - 0.8;
        }
        return 1 - damageReduction;
    }
}
