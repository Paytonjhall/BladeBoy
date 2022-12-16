package Character;

import Character.Town.Town;
import Game.UserInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NewGame {
    UserInput input = new UserInput();
    public void start(String name) {
        System.out.println("Welcome Hero!");
        input.sleep(1000);
        System.out.println("You wake up in a dark woods with little on you.");
        input.sleep(1000);
        System.out.println("You see an slime come towards you!");
        input.sleep(1000);
        System.out.println("You ready your weapon, the time is now!");
        Enemy slime = new Enemy("Slime", 20, 4, 0, 30, null, 25);
        Hero hero = new Hero(new Armor("Torn Leather Armor", 10, "Plain", .05),
                new Weapon("Chipped Wood Sword", 10, "An old chipped sword made of wood.", 4),
                new Artifact("Fortune Amulet", 10, "A old amulet from your mother", "Fortune", 1.125),
                100, 100, 0, 1, 5, null);
        hero.setUsername(name);
        Combat combat = new Combat();
        combat.fight(hero, slime);
        input.sleep(1000);
        System.out.println("Good job, Hero! You defeated your first enemy. It gets harder from here. You have 2 more enemies until you reach camp");
        input.sleep(3000);
        System.out.println("You see a goblin come towards you!");
        input.sleep(1000);
        System.out.println("You ready your weapon, Round two, fight!");
        Enemy goblin = new Enemy("Goblin", 25, 6, .1, 50, null, 55);
        combat.fight(hero, goblin);
        input.sleep(1000);
        System.out.println("The golbin fell! You have 1 more enemy until you reach camp.");
        input.sleep(3000);
        System.out.println("You can see the town in the distance, but a wolf stands in the path growling");
        input.sleep(1000);
        System.out.println("You ready your weapon, Round three, fight!");
        List<ItemInterface> wolfWeapons= new ArrayList<ItemInterface>();
        wolfWeapons.add(new Weapon("Wolf Fang", 50, "A sharp wolf fang", 6));
        Enemy wolf = new Enemy("Wolf", 15, 8, -.25, 30, wolfWeapons, 15);
        combat.fight(hero, wolf);
        input.sleep(1000);
        System.out.println("You have defeated the wolf! You have made it to camp!");
        input.sleep(3000);
        System.out.println("You have completed the tutorial. You can now rest and heal up.");
        System.out.println("Explore the town and see how you can get stronger and defeat enemies!");
        System.out.println("Be careful, if you die, you are dead forever!");
        Town town = new Town(hero);



    }
}
