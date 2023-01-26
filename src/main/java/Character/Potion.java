package Character;

public class Potion {
    String type;
    int effect;
    int value;
    String iconPath = "";

    //Types: Heal Potion, xpPotion (gives a little xp), done
    // XP Potion done
    // luckPotion (doubles next chest drop),
    // void Potion (IDK, next enemy attack miss maybe?),
    // holyPotion (remove all darkness?),
    // bloodPotion (the more health your missing, the more heal it gives?), done
    // regen potion(gain health as you move, only for 20 blocks),
    // critPotion(next hit crit),
    // devil Potion( health set to 30%, but levels you up instantly.


    public Potion(String type, int effect, int value) {
        this.type = type;
        this.effect = effect;
        this.value = value;
    }

    public Potion(String type, int effect, int value, String iconPath) {
        this.type = type;
        this.effect = effect;
        this.value = value;
        this.iconPath = iconPath;
    }

    public Potion(){

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getEffect() {
        return effect;
    }

    public void setEffect(int effect) {
        this.effect = effect;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String toString(){
        return "Potion: " + type + " - " + effect + ", gold : " + value;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }
}
