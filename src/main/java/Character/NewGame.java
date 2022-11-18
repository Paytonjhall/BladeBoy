package Character;

import Game.UserInput;

public class NewGame {
    UserInput input = new UserInput();
    public void start() {
        System.out.println("Welcome Hero!");
        input.sleep(1000);
        System.out.println("You wake up in a dark woods with little on you.");
        input.sleep(1000);
        System.out.println("You see an slime come towards you!");
        input.sleep(1000);
        System.out.println("You ready your weapon, the time is now!");
        Enemy slime = new Enemy("Slime", 25, 5, .1);
        Hero hero = new Hero(new Armor("Torn Leather Armor", 10, "Plain", .05), new Weapon("Chipped Wood Sword", 10, "An old chipped sword made of wood.", 4), new Artifact("Fortune Amulet", 10, "A old amulet from your mother", "money", .15), 100);
        Combat combat = new Combat();
        combat.fight(hero, slime);
    }
}
