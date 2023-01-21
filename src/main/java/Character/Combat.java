package Character;

import Game.Output;
import Game.Sound;
import Game.UserInput;

import java.util.Random;

public class Combat {
    UserInput userInput = new UserInput();
    int protection = 0;
    Output output = new Output();
    Hero hero;
    Enemy enemy;
    Sound sound = new Sound();
    boolean turn = true; // true = hero, false = enemy
    public void startFight (Hero hero, Enemy enemy)  {
        this.hero = hero;
        this.enemy = enemy;
        turn = true;
    }

    //Normal attack
    public Hero attack(){
        enemy.takeDamage(calculateDamage(hero, enemy));
        sound.swordHitSound();
        hero = checkHealths();
        if(hero.finishedCombat) return hero;
        turn = false;
        EnemyTurn();
        return hero;
    }


    private void EnemyTurn(){
        if(enemy.getHealth() > 0){
            hero.takeDamage(calculateDamage(enemy, hero));
            hero = checkHealths();
            turn = true;
        }
    }

    public Hero checkHealths(){
        if(enemy.health<=0) {
            //Monster has died.
            //Give gold and exp
            sound.victorySound();
            hero = awardItems(hero, enemy);
            return hero;
        } else if(hero.health<=0) {
            //Hero has died
            //Game over
            System.exit(100);
        }
        return hero;
    }

    private int calculateDamage(Hero hero, Enemy enemy){
        Random random = new Random();
        int damageRange =(int)(hero.weapon.getWeaponDamage()/2) +random.nextInt(hero.weapon.getWeaponDamage());
        int damage = (int) (damageRange * ((1- enemy.armorRating/1000)));
        damage += strength(hero);
        damage += crush(hero);
        if(critCheck(hero)) damage *= 2.5;
        double lifeSteal = lifeSteal(hero);
        if(lifeSteal > 0) {
            System.out.print("Hero life steals "); output.printRed((int)((lifeSteal * damage)/100) + ""); System.out.println(" health");
            hero.heal((int)((lifeSteal * damage)/100));
        }
        return damage;
    }

    private int calculateDamage(Enemy enemy, Hero hero){
        Random random = new Random();
        int damageRange =(int)(enemy.damage/2) +random.nextInt(enemy.damage);
        int damage =(int) (damageRange * (1-(hero.getArmor().getArmorRating()/1000)));
        damage = checkProtection(damage);
        return damage;
    }

    private int checkProtection(int damage){
        if(protection > 0){
            if(protection > damage){
                protection -= damage;
                damage = 0;
                System.out.println("Your armors protection stopped the blow: Protection remaining: " + protection);
                return damage;
            }
            else{
                damage -= protection;
                protection = 0;
                System.out.println("Your armors protected some of the blow: Protection remaining: 0");
                return damage;
            }
        }
        return damage;
    }

    private Hero awardItems(Hero hero, Enemy enemy){
        hero.addXp(enemy.getXp());
        if(enemy.getDrops() != null) {
            System.out.println("Items Found:");
            for (ItemInterface item : enemy.getDrops()) {
                System.out.println(item.getName() + ": Description - " + item.getDescription() + " | Value - " + item.getValue());
                hero.addToBag(item);
            }
        } else {
            System.out.println("The " + enemy.name + " dropped no items.");
        }
        if(enemy.getGold()>0 && hero.getArtifact() != null && hero.getArtifact().getType().equals("Fortune")){
            int extraGold = hero.getArtifact().artifactAmplify(enemy.getGold());
            System.out.print("The " + enemy.name + " dropped ");
            output.printYellow(enemy.getGold() + "");
            System.out.print(" gold, but your artifact amplifies it to ");
            output.printYellow(extraGold + "");
            System.out.println(" gold."+ " (" + hero.getArtifact().getName() + ")");
            hero.addGold(extraGold);
        } else if (enemy.getGold()>0) {
            hero.addGold(enemy.getGold());
            System.out.print("The " + enemy.name + " dropped ");
            output.printYellow(enemy.getGold() + "");
            System.out.println(" gold!");
        } else {
                System.out.println("The " + enemy.name + " dropped no gold.");
            }
        hero.inCombat = false;
        hero.finishedCombat = true;
        return hero;
    }

    private boolean critCheck(Hero hero){
        Random random = new Random();
        int baseCritChance = 5;
        int critChance = random.nextInt(100);
        for(Mystic mystic : hero.getMystics()){
            if(mystic.getBuff().equals("Critical")){
                baseCritChance *= mystic.amplifier;
            }
        }

        if(critChance <= baseCritChance){
            output.printPurple("Critical Hit!\n");
            return true;
        }
        return false;
    }

    private double lifeSteal(Hero hero){
        double lifeSteal = 0;
        for(Mystic mystic : hero.getMystics()){
            if(mystic.getBuff().equals("Vamperism")){
                lifeSteal += mystic.amplifier;

            }
        }
        return lifeSteal;
    }

    private double strength(Hero hero){
        boolean check = false;
        double strength = 1;
        for(Mystic mystic : hero.getMystics()){
            if(mystic.getBuff().equals("Strength")){
                strength += mystic.amplifier;
                check = true;
            }
        }
        if(!check) return 1;
        return strength;
    }

    private boolean block(Hero hero){
        Random random = new Random();
        int baseBlockChance = 5;
        int blockChance = random.nextInt(100);
        for(Mystic mystic : hero.getMystics()){
            if(mystic.getBuff().equals("Block")){
                baseBlockChance *= mystic.amplifier;
            }
        }

        if(blockChance <= baseBlockChance){
            return true;
        }
        return false;
    }

    private int crush(Hero hero){
        int damage = 0;
        double crushRatio = (double) hero.health / 100.00;
        for(Mystic mystic : hero.getMystics()){
            if(mystic.getBuff().equals("Crush")){
                damage += (int) (mystic.amplifier * crushRatio);
            }
        }
        return damage;
    }
}
