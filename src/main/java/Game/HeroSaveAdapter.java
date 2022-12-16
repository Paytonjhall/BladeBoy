package Game;

import java.util.List;
import java.util.ArrayList;

import Character.*;

public class HeroSaveAdapter {

    public Armor armor;
    public Weapon weapon;
    public Artifact artifact;
    public List<ItemInterface> Bag;
    public List<Mystic> mystics;
    public int health;
    public int maxHealth;
    public int xp;
    public int level;
    public int nextLevelXp;
    public int gold;
    public int dungeonCount = 0;
    public String username = " ";
    public List<Hero.SkillPoints> skillPoints;
    public PotionBag potionBag;

    public HeroSaveAdapter(Hero hero){
        this.armor = hero.getArmor();
        this.weapon = hero.getWeapon();
        this.artifact = hero.getArtifact();
        this.Bag = hero.getBag();
        this.mystics = hero.getMystics();
        this.health = hero.getHealth();
        this.maxHealth = hero.getMaxHealth();
        this.xp = hero.getXp();
        this.level = hero.getLevel();
        this.nextLevelXp = hero.getNextLevelXp();
        this.gold = hero.getGold();
        this.dungeonCount = hero.getDungeonCount();
        this.username = hero.getUsername();
        this.skillPoints = hero.getSkillPoints();
        this.potionBag = hero.getPotionBag();
    }

    public HeroSaveAdapter(Armor armor, Weapon weapon, Artifact artifact, List<ItemInterface> bag, List<Mystic> mystics, int health, int maxHealth, int xp, int level, int nextLevelXp, int gold, int dungeonCount, String username, List<Hero.SkillPoints> skillPoints, PotionBag potionBag) {
        this.armor = armor;
        this.weapon = weapon;
        this.artifact = artifact;
        Bag = bag;
        this.mystics = mystics;
        this.health = health;
        this.maxHealth = maxHealth;
        this.xp = xp;
        this.level = level;
        this.nextLevelXp = nextLevelXp;
        this.gold = gold;
        this.dungeonCount = dungeonCount;
        this.username = username;
        this.skillPoints = skillPoints;
        this.potionBag = potionBag;
    }

    @Override
    public String toString() {
        return "HeroSaveAdapter{" +
                "armor=" + armor.toString() +
                ", weapon=" + weapon +
                ", artifact=" + artifact.toString() +
                ", Bag=" + Bag +
                ", mystics=" + mystics +
                ", health=" + health +
                ", maxHealth=" + maxHealth +
                ", xp=" + xp +
                ", level=" + level +
                ", nextLevelXp=" + nextLevelXp +
                ", gold=" + gold +
                ", dungeonCount=" + dungeonCount +
                ", username='" + username + '\'' +
                ", skillPoints=" + skillPoints +
                ", potionBag=" + potionBag +
                '}';
    }
}
