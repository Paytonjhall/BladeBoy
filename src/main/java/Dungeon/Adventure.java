package Dungeon;
import Character.*;
import Character.Combat.Combat;

public class Adventure {
    Hero hero;
    int level = 0;
    int fights = 0;
    Combat combat = new Combat();
    public Adventure() {

    }

    public Hero startAdventure(Hero hero){
        this.hero = hero;
        level = hero.getDungeonCount();
        fights =(int) (3 + (Math.random() * 2));

        System.out.println("You enter the dungeon: There are " + fights + " enemies in this dungeon!");
        for(int i = 0; i < fights; i++){
            Enemy enemy = new EnemyGenerator(hero).generateEnemy();
            System.out.println("You encounter a " + enemy.getName());
            //combat.fight(hero, enemy);
        }
        hero.setDungeonCount(hero.getDungeonCount() + 1);
        return hero;
    }
}
