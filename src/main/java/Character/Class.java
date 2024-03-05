package Character;

public class Class {
    String ClassType;
    String damageType;

    public Class(String classType, String damageType) {
        ClassType = classType;
        this.damageType = damageType;
    }

    public String getClassType() {
        return ClassType;
    }

    public void setClassType(String classType) {
        ClassType = classType;
    }

    public String getDamageType() {
        return damageType;
    }

    public void setDamageType(String damageType) {
        this.damageType = damageType;
    }
}
