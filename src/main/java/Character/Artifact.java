package Character;

public class Artifact implements ItemInterface {

    String name;
    int value;
    String description;
    String type;
    double amplifier;
    public Artifact(String name, int value, String description, String type, double amplifier) {
        this.name = name;
        this.value = value;
        this.description = description;
        this.type = type;
        this.amplifier = amplifier;
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
}
