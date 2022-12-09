package Character;

import java.util.ArrayList;
import java.util.Random;

// Mystics are items that are passive buffs to your character
public class Mystic {

    String buff;
    double amplifier;
    String type;
    int level;

    String[] buffs = new String[]{"Strength", "Health", "Armor", "Vamperism", "Critical"};

    public Mystic(String buff, int amplifier, String type, int level) {
        this.buff = buff;
        this.amplifier = amplifier;
        this.type = type;
        this.level = level;
    }

    public Mystic (int level) {
        this.level = level;

    }

    public void createMystic() {

        Random rand = new Random();
        buff = buffs[rand.nextInt(buffs.length)];

        switch (buff) {
            case "Strength", "Armor":
                amplifier = (Math.floor((((Math.random() * 3) + 7) * level)) /100) + 1;
                break;
            case "Health":
                amplifier = Math.floor((Math.random() * 5) + 10 * level);
                break;
            case "Vamperism":
                amplifier = (Math.floor((((Math.random() * 2) + 3) * level)) /100) + 1;
                break;
            case "Critical":
                amplifier = (Math.floor((((Math.random() * 5) + 9) * level)) /100) + 1;
                break;
        }


    }
















    public String getBuff() {
        return buff;
    }

    public void setBuff(String buff) {
        this.buff = buff;
    }

    public double getAmplifier() {
        return amplifier;
    }

    public void setAmplifier(int amplifier) {
        this.amplifier = amplifier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String toString() {
        return "Mystic: " + buff + " " + amplifier;
    }
}
