package Character;

import java.util.Random;

public class Combat {
    public Hero fight (Hero hero, Enemy enemy)  {
        boolean fight = true;
        int damage;
        try {
            while (fight) {
                System.out.println("Hero attacks " + enemy.name);
                Thread.sleep(1000);
                damage = calculateDamage(hero, enemy);
                enemy.takeDamage(damage);
                if (enemy.health > 0)
                    System.out.println("Enemy takes " + damage + " damage. The " + enemy.name + " has " + enemy.health + " health left.");
                else {
                    System.out.println("The " + enemy.name + " has been defeated!");
                    fight = false;
                    continue;
                }
                Thread.sleep(1000);
                System.out.println("Enemy attacks Hero");
                Thread.sleep(1000);
                damage = calculateDamage(enemy, hero);
                hero.takeDamage(damage);
                if (hero.health > 0)
                    System.out.println("Hero takes " + damage + " damage. The Hero has " + hero.health + " health left.");
                else {
                    System.out.println("The Hero has been defeated!");
                    fight = false;
                }
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        awardItems(hero, enemy);

        return hero;
    }

    private int calculateDamage(Hero hero, Enemy enemy){
        Random random = new Random();
        int damageRange =(int)(hero.weapon.getWeaponDamage()/2) +random.nextInt(hero.weapon.getWeaponDamage());
        int damage = (int) (damageRange * ((1- enemy.armorRating)));
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
            System.out.println("The " + enemy.name + " dropped some items! *Make item drop system*");
        } else {
            System.out.println("The " + enemy.name + " dropped no items.");
        }
        if(enemy.getGold()>0){
            hero.addGold(enemy.getGold());
            System.out.println("The " + enemy.name + " dropped " + enemy.getGold() + " gold!");
        } else {
            System.out.println("The " + enemy.name + " dropped no gold.");
        }
        return hero;
    }
}
