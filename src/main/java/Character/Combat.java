package Character;

import Game.Output;
import Game.UserInput;

import java.util.Random;

public class Combat {
    Output output = new Output();
    public Hero fight (Hero hero, Enemy enemy)  {
        int round = 0;
        UserInput userInput = new UserInput();
        boolean fight = true;
        int damage;
        while (fight) {
            round++;
            output.printBlue("Hero Turn: ");
            userInput.checkUser();
            System.out.println("Hero attacks " + enemy.name);
            damage = calculateDamage(hero, enemy);
            enemy.takeDamage(damage);
            if (enemy.health > 0) {
                {
                    System.out.print(enemy.getName() + "takes ");
                    output.printRed(damage + "");
                    System.out.print(" damage. The "+enemy.getName()+ " has ");
                    output.printGreen(enemy.getHealth() + "");
                    System.out.println(" health left.\n");
                }
            }
            else {
                System.out.println("The " + enemy.name + " has been defeated!");
                fight = false;
                continue;
            }

            output.printRed("Enemies Turn: ");
            userInput.sleep(1000);
            System.out.println("Enemy attacks Hero");
            damage = calculateDamage(enemy, hero);
            if(!block(hero))hero.takeDamage(damage);
            else System.out.println("Hero blocked the attack");
            if (hero.health > 0) {
                System.out.print("Hero takes ");
                output.printRed(damage + "");
                System.out.print(" damage. The Hero has ");
                output.printGreen(hero.health+"");
                System.out.println(" health left \n");
            }
            else {
                System.out.println("The Hero has been defeated!");
                fight = false;
            }
        }

        awardItems(hero, enemy);
        System.out.println("You take a moment to gather your self, after combat:\n");
        hero.heroOptions();
        return hero;
    }

    private void EnemyTurn(){

    }

    private int calculateDamage(Hero hero, Enemy enemy){
        Random random = new Random();
        int damageRange =(int)(hero.weapon.getWeaponDamage()/2) +random.nextInt(hero.weapon.getWeaponDamage());
        int damage = (int) (damageRange * ((1- enemy.armorRating)));
        damage *= strength(hero);
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
        int damage =(int) (damageRange * (1-hero.getArmor().getArmorRating()));
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
                strength *= mystic.amplifier;
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
}
