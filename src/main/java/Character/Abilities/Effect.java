package Character.Abilities;

public class Effect {
    boolean onTileMove;
    int heal = 0;
    int damage = 0;
    boolean inCombatAction;
    boolean crit;
    boolean count;
    boolean luck;


    public boolean isOnTileMove() {
        return onTileMove;
    }

    public void setOnTileMove(boolean onTileMove) {
        this.onTileMove = onTileMove;
    }

    public int getHeal() {
        return heal;
    }

    public void setHeal(int heal) {
        this.heal = heal;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public boolean isInCombatAction() {
        return inCombatAction;
    }

    public void setInCombatAction(boolean inCombatAction) {
        this.inCombatAction = inCombatAction;
    }

    public boolean isCrit() {
        return crit;
    }

    public void setCrit(boolean crit) {
        this.crit = crit;
    }

    public boolean isCount() {
        return count;
    }

    public void setCount(boolean count) {
        this.count = count;
    }

    public boolean isLuck() {
        return luck;
    }

    public void setLuck(boolean luck) {
        this.luck = luck;
    }
}
