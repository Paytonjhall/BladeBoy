package Character.Stats;

public abstract class Stat {
    public abstract int value();
    public abstract int setValue(int value);
    public abstract int addValue(int add);
    public abstract int subtractValue(int sub);
    public abstract String name();
    public abstract String description();
    public abstract String icon();
    public abstract double effect();

}
