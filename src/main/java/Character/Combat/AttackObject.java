package Character.Combat;

public class AttackObject {
    // This is also how we are going to show enemies dealing damage to players before it happens.
    boolean physicalAttack = true;
    boolean magicalAttack = false;
    int damage = 0;
    double lifeSteal = 0;
    int crit = 0;
    int block = 0;
    int heal = 0;
    int buff = 0;
    boolean critHit = false;

    public AttackObject() {
    }

    public AttackObject(int damage) {
        this.damage = damage;
    }

    public AttackObject(int damage, int block) {
        this.damage = damage;
        this.block = block;
    }

    public AttackObject(int damage, int block, int heal) {
        this.damage = damage;
        this.block = block;
        this.heal = heal;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public double getLifeSteal() {
        return lifeSteal;
    }

    public void setLifeSteal(double lifeSteal) {
        this.lifeSteal = lifeSteal;
    }

    public int getCrit() {
        return crit;
    }

    public void setCrit(int crit) {
        this.crit = crit;
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public int getHeal() {
        return heal;
    }

    public void setHeal(int heal) {
        this.heal = heal;
    }

    public int getBuff() {
        return buff;
    }

    public void setBuff(int buff) {
        this.buff = buff;
    }

    public boolean isCritHit() {
        return critHit;
    }

    public void setCritHit(boolean critHit) {
        this.critHit = critHit;
    }

    public void setPhysicalAttack() {
        this.magicalAttack = false;
        this.physicalAttack = true;
    }

    public void setMagicalAttack() {
        this.magicalAttack = true;
        this.physicalAttack = false;
    }

    public boolean isPhysicalAttack() {
        return physicalAttack;
    }

    public boolean isMagicalAttack() {
        return magicalAttack;
    }

}
